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
package fixengine.messages.fix42.chix.europe;

import fixengine.messages.MessageHeader;
import fixengine.messages.Required;

import fixengine.tags.Account;
import fixengine.tags.MaxFloor;
import fixengine.tags.MinQty;
import fixengine.tags.Symbol;
import fixengine.tags.fix42.Side;
import fixengine.tags.fix42.TimeInForce;
import fixengine.tags.PegDifference;
import fixengine.tags.Price;
import fixengine.tags.OrderCapacity;
import fixengine.tags.SecurityID;
import fixengine.tags.SecurityExchange;
import fixengine.tags.ClOrdID;
import fixengine.tags.Currency;
import fixengine.tags.HandlInst;
import fixengine.tags.OrderQty;
import fixengine.tags.OrdType;
import fixengine.tags.ExpireTime;
import fixengine.tags.TransactTime;

import fixengine.tags.fix42.chix.europe.ExecInst;
import fixengine.tags.fix42.chix.europe.IDSource;

public class NewOrderSingleMessage extends fixengine.messages.fix42.NewOrderSingleMessage {
    public NewOrderSingleMessage(MessageHeader header) {
        super(header);
    }

    @Override protected void fields() {
        field(Account.Tag(), Required.NO);
        field(ClOrdID.Tag());
        field(Currency.Tag(), new Required() {
            @Override public boolean isRequired() {
                return getEnum(IDSource.Tag()).equals(IDSource.ISIN());
            }
        });
        field(ExecInst.Tag(), Required.NO);
        field(HandlInst.Tag());
        field(IDSource.Tag(), Required.NO);
        field(OrderQty.Tag());
        field(OrdType.Tag());
        field(Price.Tag(), new Required() {
            @Override public boolean isRequired() {
                return getEnum(OrdType.Tag()).equals(OrdType.Limit());
            }
        });
        field(OrderCapacity.Tag(), Required.NO);
        field(SecurityID.Tag(), new Required() {
            @Override public boolean isRequired() {
                return hasValue(IDSource.Tag());
            }
        });
        field(Side.Tag());
        field(Symbol.Tag());
        field(TimeInForce.Tag(), Required.NO);
        field(TransactTime.Tag());
        field(MinQty.Tag(), Required.NO);
        field(MaxFloor.Tag(), Required.NO);
        field(ExpireTime.Tag(), new Required() {
            @Override public boolean isRequired() {
                return getEnum(TimeInForce.Tag()).equals(TimeInForce.GoodTillDate());
            }
        });
        field(SecurityExchange.Tag(), new Required() {
            @Override public boolean isRequired() {
                return getEnum(IDSource.Tag()).equals(IDSource.ISIN());
            }
        });
        field(PegDifference.Tag(), Required.NO);
    }
}
