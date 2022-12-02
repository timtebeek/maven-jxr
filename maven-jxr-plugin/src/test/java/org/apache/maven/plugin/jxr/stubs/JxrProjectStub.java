package org.apache.maven.plugin.jxr.stubs;

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import java.io.File;
import java.util.Collections;
import java.util.List;

import org.apache.maven.RepositoryUtils;
import org.apache.maven.artifact.repository.ArtifactRepository;
import org.apache.maven.artifact.repository.ArtifactRepositoryPolicy;
import org.apache.maven.artifact.repository.MavenArtifactRepository;
import org.apache.maven.artifact.repository.layout.DefaultRepositoryLayout;
import org.apache.maven.plugin.testing.stubs.MavenProjectStub;
import org.eclipse.aether.repository.RemoteRepository;

public abstract class JxrProjectStub
    extends MavenProjectStub
{
    /**
     * @return the POM file name
     */
    protected abstract String getPOM();

    @Override
    public File getBasedir()
    {
        return new File( super.getBasedir() + "/src/test/resources/unit/" );
    }

    @Override
    public File getFile()
    {
        return new File( getBasedir(), getPOM() );
    }

    @Override
    public List<ArtifactRepository> getRemoteArtifactRepositories()
    {
        ArtifactRepository repository = new MavenArtifactRepository( "central", "https://repo1.maven.org/maven2",
                                                                       new DefaultRepositoryLayout(), new ArtifactRepositoryPolicy(), new ArtifactRepositoryPolicy() );

        return Collections.singletonList( repository );
    }

    @Override
    public List<RemoteRepository> getRemoteProjectRepositories()
    {
        return RepositoryUtils.toRepos( getRemoteArtifactRepositories() );
    }
}
