/**
 *    Copyright 2019 MetaRing s.r.l.
 * 
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 * 
 *        http://www.apache.org/licenses/LICENSE-2.0
 * 
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.metaring.framework.functionality;

import com.metaring.framework.util.StringUtil;

class ExternalException extends UnmanagedException {
    private static final long serialVersionUID = -74755583565873801L;

    ExternalException(FunctionalityStackElementSeries functionalityStackElementSeries, Exception e, int row) {
        super(functionalityStackElementSeries, StringUtil.fromThrowableToString((Throwable) e), row);
    }
}
