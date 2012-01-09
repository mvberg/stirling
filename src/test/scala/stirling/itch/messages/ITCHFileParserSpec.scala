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
package stirling.itch.messages

import silvertip.{GarbledMessageException, PartialMessageException}
import stirling.itch.Spec
import stirling.itch.templates.{MillisecondsFixtures, SecondsFixtures}

class ITCHFileParserSpec extends Spec with ITCHFileParserFixtures {
  "ITCHFileParser" must {
    val buffer = (crlf + crlf + Milliseconds.encoded + crlf + Seconds.encoded +
      crlf + crlf).toByteBuffer
    "ignore CRLFs before messages" in {
      ITCHFileParser.parse(buffer) must equal(Milliseconds.message)
    }
    "ignore CRLFs between messages" in {
      ITCHFileParser.parse(buffer) must equal(Seconds.message)
    }
    "ignore CRLFs after messages" in {
      intercept[PartialMessageException] {
        ITCHFileParser.parse(buffer)
      }
    }
  }
}

trait ITCHFileParserFixtures {
  def crlf = "\r\n"
}

object Milliseconds extends MillisecondsFixtures
object Seconds extends SecondsFixtures