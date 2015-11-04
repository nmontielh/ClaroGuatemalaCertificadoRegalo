/*
 * Decompiled with CFR 0_102.
 * 
 * Could not load the following classes:
 *  com.ibm.mq.MQC
 *  com.ibm.mq.MQEnvironment
 *  com.ibm.mq.MQException
 *  com.ibm.mq.MQGetMessageOptions
 *  com.ibm.mq.MQMessage
 *  com.ibm.mq.MQPoolToken
 *  com.ibm.mq.MQPutMessageOptions
 *  com.ibm.mq.MQQueue
 *  com.ibm.mq.MQQueueManager
 *  org.apache.log4j.Logger
 */
package com.claro.util.mq;

import com.claro.exception.CAException;
import com.ibm.mq.MQC;
import com.ibm.mq.MQEnvironment;
import com.ibm.mq.MQException;
import com.ibm.mq.MQGetMessageOptions;
import com.ibm.mq.MQMessage;
import com.ibm.mq.MQPoolToken;
import com.ibm.mq.MQPutMessageOptions;
import com.ibm.mq.MQQueue;
import com.ibm.mq.MQQueueManager;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Hashtable;
import org.apache.log4j.Logger;

public final class UtilMQ {
    public static final Logger log = Logger.getLogger((Class)UtilMQ.class);

    public static void respaldaMensaje(String mensaje) throws CAException {
        MQPoolToken token = new MQPoolToken();
        UtilMQ.mqEscribeMensaje(mensaje, token);
        MQEnvironment.removeConnectionPoolToken((MQPoolToken)token);
    }

    private static void mqEscribeMensaje(String mensaje, MQPoolToken token) throws CAException {
        MQQueueManager qManager = null;
        MQQueue queue = null;
        int openOptions = 16;
        try {
            try {
                MQEnvironment.channel = "CANAL";
                MQEnvironment.hostname = "10.203.15.161";
                String qmName = "QMCRM30";
                String colaIn = "CA.REDENCION.MENSAJESSMS.IN";
                MQEnvironment.addConnectionPoolToken((MQPoolToken)token);
                qManager = new MQQueueManager(qmName);
                queue = qManager.accessQueue(colaIn, openOptions);
                MQMessage mqMsg = new MQMessage();
                log.info((Object)("Enviando mensaje a: " + MQEnvironment.hostname));
                mqMsg.writeUTF(mensaje);
                MQPutMessageOptions pmo = new MQPutMessageOptions();
                queue.put(mqMsg, pmo);
            }
            catch (MQException e) {
                log.info((Object)"Error al conectarse al enviar el mensaje.");
                throw new CAException(-1, "UtilMQ:mqEscribeMensaje: [Error al conectarse a la queue " + e.toString() + "]");
            }
            catch (IOException e) {
                log.info((Object)"Error al conectarse al enviar el mensaje.");
                throw new CAException(-1, "UtilMQ:mqEscribeMensaje: [Error al conectarse a la queue " + e.toString() + "]");
            }
            catch (Exception e) {
                log.info((Object)"Error al conectarse al enviar el mensaje.");
                throw new CAException(-1, "UtilMQ:mqEscribeMensaje: [Error al conectarse a la queue " + e.toString() + "]");
            }
        }
        finally {
            try {
                if (queue != null && queue.isOpen()) {
                    queue.close();
                }
                if (qManager != null) {
                    qManager.disconnect();
                    if (qManager.isOpen()) {
                        qManager.close();
                    }
                }
            }
            catch (MQException var10_14) {}
        }
    }

    public String mqRead(String idQueue, MQPoolToken token) throws CAException {
        String strMsg;
        MQQueueManager qManager = null;
        MQQueue queue = null;
        String channel = "CANAL";
        String host = "10.203.15.161";
        String qmName = "QMCRM30";
        String colaIn = "CA.REDENCION.MENSAJESSMS.IN";
        strMsg = null;
        Hashtable<String, String> ht = null;
        ht = new Hashtable<String, String>();
        ht.put("hostname", host);
        ht.put("channel", channel);
        try {
            try {
                qManager = new MQQueueManager(qmName, ht);
                int openOptions = 49;
                MQMessage msg = new MQMessage();
                msg.messageId = MQC.MQMI_NONE;
                msg.correlationId = MQC.MQCI_NONE;
                MQGetMessageOptions pmo = new MQGetMessageOptions();
                pmo.options = 0;
                pmo.waitInterval = -1;
                queue = qManager.accessQueue(colaIn, openOptions, null, null, null);
                if (queue.getCurrentDepth() != 0) {
                    queue.get(msg, pmo);
                    strMsg = msg.readUTF();
                    try {
                        Thread.sleep(5);
                    }
                    catch (InterruptedException e) {
                        System.out.println("Imposible optener el mensaje " + e.toString());
                    }
                } else {
                    strMsg = "0";
                }
            }
            catch (IOException e) {
                throw new CAException(-1, "UtilMQ:mqRead: [Error al conectarse a la queue " + e.toString() + "]");
            }
            catch (MQException e) {
                throw new CAException(-1, "UtilMQ:mqRead: [Error al conectarse a la queue " + e.toString() + "]");
            }
        }
        finally {
            try {
                if (queue != null && queue.isOpen()) {
                    queue.close();
                }
                if (qManager != null) {
                    qManager.disconnect();
                    if (qManager.isOpen()) {
                        qManager.close();
                    }
                }
            }
            catch (MQException var16_19) {}
        }
        return strMsg;
    }
}

