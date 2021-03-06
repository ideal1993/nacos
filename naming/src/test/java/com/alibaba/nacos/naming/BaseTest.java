/*
 * Copyright 1999-2018 Alibaba Group Holding Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alibaba.nacos.naming;

import com.alibaba.nacos.naming.consistency.persistent.raft.RaftCore;
import com.alibaba.nacos.naming.consistency.persistent.raft.RaftPeer;
import com.alibaba.nacos.naming.consistency.persistent.raft.RaftPeerSet;
import com.alibaba.nacos.naming.core.ServiceManager;
import com.alibaba.nacos.naming.misc.NetUtils;
import org.junit.Before;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * @author nkorange
 */
public class BaseTest {

    @Mock
    public ServiceManager serviceManager;

    @Mock
    public RaftPeerSet peerSet;

    @Mock
    public RaftCore raftCore;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);

        RaftPeer peer = new RaftPeer();
        peer.ip = NetUtils.localServer();
        raftCore.setPeerSet(peerSet);
        Mockito.when(peerSet.local()).thenReturn(peer);
        Mockito.when(peerSet.getLeader()).thenReturn(peer);
        Mockito.when(peerSet.isLeader(NetUtils.localServer())).thenReturn(true);
    }
}
