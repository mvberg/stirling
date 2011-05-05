/*
 * Copyright 2010 the original author or authors.
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
package fixengine.examples.console.commands;

import static fixengine.messages.MsgTypeValue.LOGON;

import fixengine.examples.console.ConsoleClient;
import fixengine.messages.LogonMessage;
import fixengine.messages.Message;
import fixengine.messages.MessageFactory;
import fixengine.tags.fix42.EncryptMethod;
import fixengine.tags.fix42.HeartBtInt;

public class Logon extends FixMessageCommand {
  @Override protected Message newMessage(ConsoleClient client) {
    MessageFactory messageFactory = client.getMessageFactory();
    LogonMessage message = (LogonMessage) messageFactory.create(LOGON);
    message.setInteger(HeartBtInt.Tag(), 30);
    message.setEnum(EncryptMethod.Tag(), EncryptMethod.None());
    return message;
  }

  @Override protected boolean isModifyingOrderMessage() {
    return false;
  }

  @Override public String usage() {
    return "<Argument=value>* : Creates and sends logon message.";
  }
}
