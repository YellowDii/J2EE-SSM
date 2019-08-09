package nju.software.ssm.service.impl;

import nju.software.ssm.dao.ISysLogDao;
import nju.software.ssm.domain.SysLog;
import nju.software.ssm.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SysLogServiceImpl implements ISysLogService {
    @Autowired
    private ISysLogDao sysLogDao;
    @Override
    public void save(SysLog sysLogs) throws Exception {
        sysLogDao.save(sysLogs);
    }

    @Override
    public List<SysLog> findAll() throws Exception {
        return sysLogDao.findAll();
    }
}
