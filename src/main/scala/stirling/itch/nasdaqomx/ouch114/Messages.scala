/*
 * Copyright 2013 the original author or authors.
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
package stirling.itch.nasdaqomx.ouch114

import scala.annotation.switch
import stirling.itch.ByteString

sealed trait Message {
  def payload: ByteString

  def timestamp   = payload.slice(0, 8).toLong
  def messageType = payload.byteAt(8)

  override def toString = payload.toString
}

object Message {
  val messageTypeOffset = 8
}

sealed trait MessageType {
  def apply(payload: ByteString): Message

  def size(messageType: Byte): Int
}

/*
 * Section 2.1
 */
class SystemEvent(val payload: ByteString) extends Message {
  def eventCode: Byte = payload.byteAt(9)
}

object SystemEvent extends MessageType {
  def apply(payload: ByteString) = new SystemEvent(payload)

  def size(messageType: Byte) = 10
}

/*
 * Section 2.2.1
 */
class OrderAccepted(val payload: ByteString) extends Message {
  def orderToken:           ByteString = payload.slice(  9, 14)
  def buySellIndicator:     Byte       = payload.byteAt(23)
  def quantity:             Long       = payload.slice( 24,  9).toLong
  def orderBook:            Long       = payload.slice( 33,  6).toLong
  def price:                Long       = payload.slice( 39, 10).toLong
  def timeInForce:          Long       = payload.slice( 49,  5).toLong
  def firm:                 ByteString = payload.slice( 54,  4)
  def display:              Byte       = payload.byteAt(58)
  def orderReferenceNumber: Long       = payload.slice( 59,  9).toLong
  def capacity:             Byte       = payload.byteAt(68)
  def user:                 ByteString = payload.slice( 69,  6)
  def clientReference:      ByteString = payload.slice( 75, 15)
  def orderReference:       ByteString = payload.slice( 90, 10)
  def clearingFirm:         ByteString = payload.slice(100,  4)
  def clearingAccount:      ByteString = payload.slice(104, 12)
  def minimumQuantity:      Long       = payload.slice(116,  9).toLong
  def crossType:            Byte       = payload.byteAt(125)
}

object OrderAccepted extends MessageType {
  def apply(payload: ByteString) = new OrderAccepted(payload)

  def size(messageType: Byte) = (messageType: @switch) match {
    case 'A' => 125
    case 'R' => 126
  }
}

/*
 * Section 2.2.2
 */
class CanceledOrder(val payload: ByteString) extends Message {
  def orderToken:        ByteString = payload.slice( 9, 14)
  def decrementQuantity: Long       = payload.slice(23,  9).toLong
  def reason:            Byte       = payload.byteAt(32) 
}

object CanceledOrder extends MessageType {
  def apply(payload: ByteString) = new CanceledOrder(payload)

  def size(messageType: Byte) = 33
}

/*
 * Section 2.2.3
 */
class ExecutedOrder(val payload: ByteString) extends Message {
  def orderToken:       ByteString = payload.slice( 9, 14)
  def executedQuantity: Long       = payload.slice(23,  9).toLong
  def executionPrice:   Long       = payload.slice(32, 10).toLong
  def liquidityFlag:    Byte       = payload.byteAt(42)
  def matchNumber:      Long       = payload.slice(43,  9).toLong
  def contraFirm:       ByteString = payload.slice(52,  4)
}

object ExecutedOrder extends MessageType {
  def apply(payload: ByteString) = new ExecutedOrder(payload)

  def size(messageType: Byte) = 56
}

/*
 * Section 2.2.4
 */
class BrokenTrade(val payload: ByteString) extends Message {
  def orderToken:  ByteString = payload.slice( 9, 14)
  def matchNumber: Long       = payload.slice(23,  9).toLong
  def reason:      Byte       = payload.byteAt(32)
}

object BrokenTrade extends MessageType {
  def apply(payload: ByteString) = new BrokenTrade(payload)

  def size(messageType: Byte) = 33
}

/*
 * Section 2.2.5
 */
class RejectedOrder(val payload: ByteString) extends Message {
  def orderToken: ByteString = payload.slice(9, 14)
  def reason:     Byte       = payload.byteAt(23)
}

object RejectedOrder extends MessageType {
  def apply(payload: ByteString) = new RejectedOrder(payload)

  def size(messageType: Byte) = 24
}

/*
 * Section 2.2.6
 */
class CancelPending(val payload: ByteString) extends Message {
  def orderToken: ByteString = payload.slice(9, 14)
}

object CancelPending extends MessageType {
  def apply(payload: ByteString) = new CancelPending(payload)

  def size(messageType: Byte) = 23
}

/*
 * Section 2.2.7
 */
class MMORefreshRequest(val payload: ByteString) extends Message {
  def firm:       ByteString = payload.slice( 9, 4)
  def orderBook:  Long       = payload.slice(13, 6).toLong
}

object MMORefreshRequest extends MessageType {
  def apply(payload: ByteString) = new MMORefreshRequest(payload)

  def size(messageType: Byte) = 19
}
