package nju.software.ssm.service;

import nju.software.ssm.domain.SysLog;

import java.util.List;

public interface ISysLogService {
    void save(SysLog sysLogs) throws Exception;

    List<SysLog> findAll()throws Exception;
}
