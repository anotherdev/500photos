package com.anotherdev.photos500.domain;

import com.anotherdev.photos500.app.P5App;
import com.anotherdev.photos500.repository.PhotoRepository;
import com.karumi.rosie.domain.usecase.TaskScheduler;
import com.karumi.rosie.domain.usecase.UseCaseHandler;
import com.karumi.rosie.domain.usecase.error.ErrorHandler;
import com.karumi.rosie.domain.usecase.jobqueue.TaskSchedulerJobQueue;
import com.path.android.jobqueue.JobManager;
import com.path.android.jobqueue.config.Configuration;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RosieAndroidModule {

    private static final int MIN_CONSUMER_COUNT = 1;
    private static final int MAX_CONSUMER_COUNT = 5;
    private static final int LOAD_FACTOR = 1;


    @Provides @Singleton public JobManager provideJobManager(P5App app) {
        Configuration config = new Configuration.Builder(app).minConsumerCount(MIN_CONSUMER_COUNT)
                .maxConsumerCount(MAX_CONSUMER_COUNT)
                .loadFactor(LOAD_FACTOR)
                .build();
        return new JobManager(app, config);
    }

    @Provides @Singleton public TaskScheduler provideTaskScheduler(JobManager jobManager) {
        return new TaskSchedulerJobQueue(jobManager);
    }

    @Provides @Singleton public UseCaseHandler provideUseCaseHandler(TaskScheduler taskScheduler, ErrorHandler errorHandler) {
        return new UseCaseHandler(taskScheduler, errorHandler);
    }

    @Provides public GetPhotosInCategory provideGetPhotosInCategoryUseCase() {
        return new GetPhotosInCategory(new PhotoRepository());
    }
}
