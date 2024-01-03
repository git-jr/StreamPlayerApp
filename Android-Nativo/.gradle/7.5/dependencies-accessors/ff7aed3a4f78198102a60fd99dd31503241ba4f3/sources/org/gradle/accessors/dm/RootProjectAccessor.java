package org.gradle.accessors.dm;

import org.gradle.api.NonNullApi;
import org.gradle.api.artifacts.ProjectDependency;
import org.gradle.api.internal.artifacts.dependencies.ProjectDependencyInternal;
import org.gradle.api.internal.artifacts.DefaultProjectDependencyFactory;
import org.gradle.api.internal.artifacts.dsl.dependencies.ProjectFinder;
import org.gradle.api.internal.catalog.DelegatingProjectDependency;
import org.gradle.api.internal.catalog.TypeSafeProjectDependencyFactory;
import javax.inject.Inject;

@NonNullApi
public class RootProjectAccessor extends TypeSafeProjectDependencyFactory {


    @Inject
    public RootProjectAccessor(DefaultProjectDependencyFactory factory, ProjectFinder finder) {
        super(factory, finder);
    }

    /**
     * Creates a project dependency on the project at path ":"
     */
    public AndroidComposeProjectDependency getAndroidCompose() { return new AndroidComposeProjectDependency(getFactory(), create(":")); }

    /**
     * Creates a project dependency on the project at path ":app"
     */
    public AppProjectDependency getApp() { return new AppProjectDependency(getFactory(), create(":app")); }

    /**
     * Creates a project dependency on the project at path ":core-navigation"
     */
    public CoreNavigationProjectDependency getCoreNavigation() { return new CoreNavigationProjectDependency(getFactory(), create(":core-navigation")); }

    /**
     * Creates a project dependency on the project at path ":core-networking"
     */
    public CoreNetworkingProjectDependency getCoreNetworking() { return new CoreNetworkingProjectDependency(getFactory(), create(":core-networking")); }

    /**
     * Creates a project dependency on the project at path ":core-shared"
     */
    public CoreSharedProjectDependency getCoreShared() { return new CoreSharedProjectDependency(getFactory(), create(":core-shared")); }

    /**
     * Creates a project dependency on the project at path ":core-shared-ui"
     */
    public CoreSharedUiProjectDependency getCoreSharedUi() { return new CoreSharedUiProjectDependency(getFactory(), create(":core-shared-ui")); }

    /**
     * Creates a project dependency on the project at path ":feature-list-streams"
     */
    public FeatureListStreamsProjectDependency getFeatureListStreams() { return new FeatureListStreamsProjectDependency(getFactory(), create(":feature-list-streams")); }

}
