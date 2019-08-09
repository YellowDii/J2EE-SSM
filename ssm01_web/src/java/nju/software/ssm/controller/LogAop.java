package nju.software.ssm.controller;

import nju.software.ssm.domain.SysLog;
import nju.software.ssm.service.ISysLogService;
import nju.software.ssm.utils.DateUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.ref.ReferenceQueue;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.SplittableRandom;

@Component
@Aspect
public class LogAop {
    @Autowired
    private HttpServletRequest request;//要在web配置监听器 RequestContextListener
    private Date visitTime;//开始时间
    private Class clazz;//访问的类
    private Method method;//访问的方法
    @Autowired
    private ISysLogService sysLogService;//服务层接口

    //前置通知 获取访问信息
    @Before("execution(* nju.software.ssm.controller.*.*(..))")
    public void doBefore(JoinPoint jp) throws NoSuchMethodException {
        visitTime = new Date();//当前时间就是开始访问的时间
        clazz=jp.getTarget().getClass();//获取访问类
        String methodName=jp.getSignature().getName();//获取访问方法名称
        Object[] args=jp.getArgs();//获取的访问的参数

        //判断参数是否为空
        if(args==null||args.length==0){
            method = clazz.getMethod(methodName);//只能获取无参数的方法
        }else {
            Class[] classArgs=new Class[args.length];
            for(int i=0;i<args.length;i++){
                classArgs[i]=args[i].getClass();
            }
            method = clazz.getMethod(methodName,classArgs);//获取有参数的方法
        }

    }

    //后置通知
    @After("execution(* nju.software.ssm.controller.*.*(..))")
    public void doAfter(JoinPoint jp) throws Exception {
        long time=new Date().getTime()-visitTime.getTime();//获取访问时长
        String url="";//初试url

        if(clazz!=null&&method!=null&&clazz!=LogAop.class){
            //获取类上的RequestMapping value值，例如/user
            RequestMapping classAnnotation = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
            if(classAnnotation!=null){
                String[] classValue = classAnnotation.value();
                //获取url
                url+=classValue[0];
                //获取方法的RequestMapping,例如/findAll.do
                RequestMapping methodAnnotation =  method.getAnnotation(RequestMapping.class);
                String[] methodValue = methodAnnotation.value();
                if (methodValue!=null){
                    url+=methodValue[0];
                }
                //获取request中的信息
                String ip=request.getRemoteAddr(); //获取访问IP
                //获取用户信息 利用Spring Seurity
                SecurityContext context= SecurityContextHolder.getContext();//先获取上下文
                //或者这样写：  request.getSession().getAttribute("SPRING_SECURITY_CONTEXT")
                User user=(User) context.getAuthentication().getPrincipal();
                String userName =user.getUsername();

                //将获取到的信息封装
                SysLog sysLog=new SysLog();
                sysLog.setIp(ip);
                sysLog.setExecutionTime(time);
                sysLog.setUrl(url);
                sysLog.setMethod("[类名] "+clazz.getName()+"[方法名] "+method.getName());
                sysLog.setUsername(userName);
                sysLog.setVisitTime(visitTime);
                sysLog.setVisitTimeStr(DateUtils.date2String(visitTime,"yyyy-MM-dd HH:mm"));
                //保存
                sysLogService.save(sysLog);
            }
        }
    }
}
