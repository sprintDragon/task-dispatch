/*
 * Copyright (C) 2010-2101 Alibaba Group Holding Limited.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.sprintdragon.task.dispatch.test.communication;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.sprintdragon.task.dispatch.shared.communication.core.CommunicationClient;
import org.sprintdragon.task.dispatch.shared.communication.core.CommunicationRegistry;
import org.sprintdragon.task.dispatch.shared.communication.core.exception.CommunicationException;
import org.sprintdragon.task.dispatch.shared.communication.core.impl.DefaultCommunicationClientImpl;
import org.sprintdragon.task.dispatch.shared.communication.core.impl.connection.CommunicationConnectionPoolFactory;
import org.sprintdragon.task.dispatch.shared.communication.core.impl.rmi.RmiCommunicationConnectionFactory;
import org.sprintdragon.task.dispatch.shared.communication.core.impl.rmi.RmiCommunicationEndpoint;
import org.sprintdragon.task.dispatch.test.communication.app.CommunicationAppService;
import org.sprintdragon.task.dispatch.test.communication.app.CommunicationAppServiceImpl;
import org.sprintdragon.task.dispatch.test.communication.app.event.AppCreateEvent;
import org.sprintdragon.task.dispatch.test.communication.app.event.AppEventType;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

/**
 * 测试下基于rmi通讯的功能
 * 
 * @author jianghang 2011-9-13 下午04:03:38
 */
public class RmiCommunicationTest {

    private CommunicationClient client  = null;
    private CommunicationAppService service = new CommunicationAppServiceImpl();

    @Before
    public void initial() {
        RmiCommunicationEndpoint endpoint1099 = new RmiCommunicationEndpoint(1099);
        endpoint1099.setAlwaysCreateRegistry(false);
        endpoint1099.initial();

        RmiCommunicationEndpoint endpoint1098 = new RmiCommunicationEndpoint(1098);
        endpoint1098.setAlwaysCreateRegistry(false);
        endpoint1098.initial();

        CommunicationConnectionPoolFactory factory = new CommunicationConnectionPoolFactory(
                                                                                            new RmiCommunicationConnectionFactory());
        factory.initial();
        client = new DefaultCommunicationClientImpl(factory);
        client.initial();
    }

    @Test
    public void testRmi_createEvent() {// 测试基本类型的序列化
        CommunicationRegistry.regist(AppEventType.create, service);
        AppCreateEvent event = new AppCreateEvent();
        event.setName("rmiEvent");
        event.setIntValue(1);
        event.setBoolValue(false);
        event.setFloatValue(1.0f);
        event.setDoubleValue(1.0d);
        event.setLongValue(1l);
        event.setCharValue('a');
        event.setShortValue((short) 1);
        event.setByteValue((byte) 1);
        event.setIntegerValue(new Integer("1"));
        event.setBoolObjValue(new Boolean("false"));
        event.setFloatObjValue(new Float("1.0"));
        event.setDoubleObjValue(new Double("1.0"));
        event.setLongObjValue(new Long("1"));
        event.setCharacterValue('a');
        event.setShortObjValue(new Short("1"));
        event.setByteObjValue(new Byte("1"));

        Object result = client.call("127.0.0.1:1099", event);// 同步调用
        Assert.assertTrue((Boolean) result);
    }

//    @Test
//    public void testRmi_updateEvent() { // 测试复合对象的序列化
//        CommunicationRegistry.regist(AppEventType.update, service);
//        AppUpdateEvent event = new AppUpdateEvent();
//        event.setName("rmiEvent");
//        event.setBigDecimalValue(BigDecimal.TEN);
//        event.setBigIntegerValue(BigInteger.TEN);
//        UpdateData data = new UpdateData();
//        data.setName("data");
//        data.setBigDecimalValue(BigDecimal.TEN);
//        data.setBigIntegerValue(BigInteger.TEN);
//        event.setData(data);
//        List<Boolean> result = (List<Boolean>) client.call(new String[] { "127.0.0.1:1099", "127.0.0.1:1098" }, event);// 同步调用
//
//        want.number(result.size()).isEqualTo(2);
//        want.bool(result.get(0)).is(true);
//        want.bool(result.get(1)).is(true);
//
//    }
//
//    @Test
//    public void testRmi_deleteEvent() { // 测试异步调用+多节点
//        CommunicationRegistry.regist(AppEventType.update, service);
//        AppDeleteEvent event = new AppDeleteEvent();
//        event.setName("rmiEvent");
//
//        client.call(new String[] { "127.0.0.1:1099", "127.0.0.1:1098" }, event, new Callback<List<Boolean>>() {
//
//            public void call(List<Boolean> event) {
//                want.number(event.size()).isEqualTo(2);
//                want.bool(event.get(0)).is(true);
//                want.bool(event.get(1)).is(true);
//            }
//        });// 异步调用
//    }

//    @Test(expectedExceptions = CommunicationException.class)
//    public void testRmi_FindEvent_Exception() { // 返回异常结果
//        CommunicationRegistry.regist(AppEventType.find, service);
//        AppFindEvent event = new AppFindEvent();
//        event.setName("rmiEvent-Not-Found");
//
//        client.call("127.0.0.1:1099", event);// 同步调用
//    }
}
