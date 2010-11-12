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
package fixengine.messages.fix44;

import fixengine.messages.AbstractNewOrderSingleMessage;
import fixengine.messages.MessageHeader;
import fixengine.messages.Required;
import fixengine.tags.ClOrdID;
import fixengine.tags.Currency;
import fixengine.tags.ExDestination;
import fixengine.tags.HandlInst;
import fixengine.tags.MaturityMonthYear;
import fixengine.tags.OrdType;
import fixengine.tags.OrderQty;
import fixengine.tags.Price;
import fixengine.tags.SecurityType;
import fixengine.tags.Side;
import fixengine.tags.Symbol;
import fixengine.tags.TimeInForce;
import fixengine.tags.TransactTime;
import fixengine.tags.fix42.CustomerOrFirm;

public class NewOrderSingleMessage extends AbstractNewOrderSingleMessage {
    public NewOrderSingleMessage(MessageHeader header) {
        super(header);
    }

    @Override protected void fields() {
        field(ClOrdID.TAG);
        field(Currency.TAG, Required.NO);
        field(HandlInst.TAG);
        field(ExDestination.TAG);
        field(Side.TAG);
        field(TransactTime.TAG);
        field(OrdType.TAG);
        field(Symbol.TAG);
        field(SecurityType.TAG, Required.NO);
        field(MaturityMonthYear.TAG, Required.NO);
        field(OrderQty.TAG);
        field(CustomerOrFirm.TAG, Required.NO);
        field(Price.TAG, Required.NO);
        field(TimeInForce.TAG, Required.NO);
    }
}