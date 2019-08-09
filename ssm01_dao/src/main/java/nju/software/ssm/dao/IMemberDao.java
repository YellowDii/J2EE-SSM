package nju.software.ssm.dao;

import nju.software.ssm.domain.Member;
import org.apache.ibatis.annotations.Select;

public interface IMemberDao {
    @Select("select * from member where id=#{id}")
    public Member findById(Integer id) throws Exception;
}
