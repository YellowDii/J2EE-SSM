package nju.software.ssm.dao;

import nju.software.ssm.domain.Traveller;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ITravellerDao {
    @Select("SELECT * FROM traveller WHERE id IN (SELECT travellerId FROM order_traveller WHERE orderId=#{ordersId})")
    public List<Traveller> findByOrdersId(Integer ordersId) throws Exception;
}
