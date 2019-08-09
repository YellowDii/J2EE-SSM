package nju.software.ssm.service;

import nju.software.ssm.domain.Orders;

import java.util.List;

public interface IOrdersService {

    List<Orders> findAll(int page,int size) throws Exception;

    Orders findById(Integer ordersId) throws Exception;
}
