package com.battcn.repository;


import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;

public class OrderTradeRecordJpaRepositoryTest {

    public static void main(String[] args) {
        try {
            //设置集群名称
            Settings settings = Settings.builder().put("cluster.name", "elasticsearch").build();
            //创建client
            TransportClient client = new PreBuiltTransportClient(settings).addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("192.168.0.133"), 9300));
            //搜索数据
            GetResponse response = client.prepareGet("region", "county", "050502").execute().actionGet();
            //输出结果
            System.out.println(response.getSourceAsString());
            //关闭client
            client.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
