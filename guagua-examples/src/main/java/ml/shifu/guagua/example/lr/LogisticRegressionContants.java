/*
 * Copyright [2013-2014] eBay Software Foundation
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ml.shifu.guagua.example.lr;

public final class LogisticRegressionContants {

    // avoid new
    private LogisticRegressionContants() {
    }

    public static final String LR_INPUT_NUM = "lr.input.num";

    public static final int LR_INPUT_DEFAULT_NUM = 2;

    public static final String LR_LEARNING_RATE = "lr.learning.rate";

    public static final double LR_LEARNING_DEFAULT_RATE = 0.1d;

}
