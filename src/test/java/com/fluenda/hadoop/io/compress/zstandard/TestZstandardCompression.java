/*
 * (C) Copyright 2016-2017 Fluenda.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.fluenda.hadoop.io.compress.zstandard;

import org.apache.hadoop.conf.Configuration;

import com.fluenda.hadoop.io.compress.ZstandardCodec;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.CompressionCodecFactory;
import org.apache.hadoop.io.compress.CompressionInputStream;

import org.apache.commons.io.IOUtils;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;

import java.io.IOException;

import java.io.InputStreamReader;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TestZstandardCompression {

    @Test
    public void testRecognizedCompression() throws IOException {
        org.apache.hadoop.fs.Path zstPath = new org.apache.hadoop.fs.Path("src/test/resources/sample_data.zst");
        Configuration conf = new Configuration();
        conf.set("io.compression.codecs", "com.fluenda.hadoop.io.compress.ZstandardCodec");
        CompressionCodec codec = new CompressionCodecFactory(conf).getCodec(zstPath);
        Assert.assertTrue(codec instanceof ZstandardCodec);
    }

    @Test
    public void testDecompression() throws IOException {
        ZstandardCodec codec = new ZstandardCodec();

        Path uncompressedSample = Paths.get("src/test/resources/sample_data.uncompressed");
        Path compressedSample = Paths.get("src/test/resources/sample_data.zst");

        byte[] compressed = Files.readAllBytes(compressedSample);
        byte[] uncompressedOriginal = Files.readAllBytes(uncompressedSample);

        CompressionInputStream in = codec.createInputStream(new ByteArrayInputStream(compressed));
        InputStreamReader reader = new InputStreamReader(in);
        String output;

        try (BufferedReader buffered = new BufferedReader(reader)) {
            output = IOUtils.toString(buffered);
        }

        Assert.assertEquals(IOUtils.toString(uncompressedOriginal), output);
    }
}