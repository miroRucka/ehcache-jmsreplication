/**
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 * @author Pierre Monestie (pmonestie__REMOVE__THIS__@gmail.com)
 * @author <a href="mailto:gluck@gregluck.com">Greg Luck</a>
 * @version $Id: ActiveMQJMSReplicationTest.java 816 2008-10-17 12:34:50Z gregluck $
 */

package net.sf.ehcache.distribution.jms;

import org.apache.activemq.broker.BrokerService;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import java.io.File;
import java.net.URL;

/**
 * ActiveMQ seems to have a bug in 5.1 where it does not cleanup temporary queues, even though they have been
 * deleted. That bug appears to be long standing. 5.2 as of 10/08 was not released.
 * http://www.nabble.com/Memory-Leak-Using-Temporary-Queues-td11218217.html#a11218217
 * http://issues.apache.org/activemq/browse/AMQ-1255
 */
public class ActiveMQJMSReplicationTest extends AbstractJMSReplicationTest {

    protected URL getConfiguration() {
        return ActiveMQJMSReplicationTest.class.getResource("/distribution/jms/ehcache-distributed-jms-activemq.xml");
    }

    private static final BrokerService BROKER = new BrokerService();
    
    @BeforeClass
    public static void startActiveMQ() throws Exception {
      BROKER.addConnector("tcp://localhost:61616");
      BROKER.setDataDirectoryFile(new File("target/activemq"));
      BROKER.setUseLocalHostBrokerName(true);
      BROKER.start();
      BROKER.waitUntilStarted();
    }
    
    @AfterClass
    public static void stopActiveMQ() throws Exception {
      BROKER.stop();
      BROKER.waitUntilStopped();
    }
}
