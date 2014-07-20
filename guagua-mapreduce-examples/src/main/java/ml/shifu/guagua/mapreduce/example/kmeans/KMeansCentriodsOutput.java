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
package ml.shifu.guagua.mapreduce.example.kmeans;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import ml.shifu.guagua.master.BasicMasterInterceptor;
import ml.shifu.guagua.master.MasterContext;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * {@link KMeansCentriodsOutput} is used to write the final k centers to file system.
 */
public class KMeansCentriodsOutput extends BasicMasterInterceptor<KMeansMasterParams, KMeansWorkerParams> {

    private static final Logger LOG = LoggerFactory.getLogger(KMeansCentriodsOutput.class);

    @Override
    public void postApplication(MasterContext<KMeansMasterParams, KMeansWorkerParams> context) {
        LOG.info("KMeansCentersOutput starts to write k centers to file.");

        Path out = new Path(context.getProps().getProperty(KMeansContants.KMEANS_CENTERS_OUTPUT));
        PrintWriter pw = null;
        try {
            FSDataOutputStream fos = FileSystem.get(new Configuration()).create(out);
            LOG.info("Writing results to {}", out.toString());
            pw = new PrintWriter(fos);
            KMeansMasterParams masterResult = context.getMasterResult();
            for(double[] center: masterResult.getPointList()) {
                pw.println(Arrays.toString(center));
            }
            pw.flush();
        } catch (IOException e) {
            LOG.error("Error in writing output.", e);
        } finally {
            IOUtils.closeStream(pw);
        }
    }

}
