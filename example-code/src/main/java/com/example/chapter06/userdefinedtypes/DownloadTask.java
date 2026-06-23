package com.example.chapter06.userdefinedtypes;

public class DownloadTask {
    private static final int PROGRESS_PERCENT_SCALE = 100;

    private static int sCreatedTaskCount;
    private static int sNextId = 1;

    private final int mId;
    private final String mFileName;
    private final long mTotalBytes;

    private EDownloadStatus mStatus;
    private long mDownloadedBytes;

    public DownloadTask(String fileNameOrNull, long totalBytes) {
        ++sCreatedTaskCount;
        mId = sNextId;
        ++sNextId;

        if (fileNameOrNull == null || fileNameOrNull.isBlank()) {
            mFileName = "unknown";
        } else {
            mFileName = fileNameOrNull;
        }

        if (totalBytes < 0) {
            mTotalBytes = 0;
        } else {
            mTotalBytes = totalBytes;
        }

        mStatus = EDownloadStatus.QUEUED;
        mDownloadedBytes = 0;
    }

    public static int getCreatedTaskCount() {
        return sCreatedTaskCount;
    }

    public int getId() {
        return mId;
    }

    public String getFileName() {
        return mFileName;
    }

    public long getTotalBytes() {
        return mTotalBytes;
    }

    public long getDownloadedBytes() {
        return mDownloadedBytes;
    }

    public EDownloadStatus getStatus() {
        return mStatus;
    }

    public boolean isCompleted() {
        return mStatus == EDownloadStatus.COMPLETED;
    }

    public int getProgressPercent() {
        if (mTotalBytes == 0) {
            return PROGRESS_PERCENT_SCALE;
        }

        return (int) (mDownloadedBytes * PROGRESS_PERCENT_SCALE / mTotalBytes);
    }

    public void start() {
        if (mStatus != EDownloadStatus.QUEUED) {
            return;
        }

        mStatus = EDownloadStatus.DOWNLOADING;
    }

    public void addProgress(long additionalDownloadedBytes) {
        if (mStatus != EDownloadStatus.DOWNLOADING) {
            return;
        }

        if (additionalDownloadedBytes <= 0) {
            return;
        }

        mDownloadedBytes += additionalDownloadedBytes;

        if (mDownloadedBytes >= mTotalBytes) {
            mDownloadedBytes = mTotalBytes;
            mStatus = EDownloadStatus.COMPLETED;
        }
    }

    public void fail() {
        if (mStatus == EDownloadStatus.COMPLETED || mStatus == EDownloadStatus.CANCELED) {
            return;
        }

        mStatus = EDownloadStatus.FAILED;
    }

    public void cancel() {
        if (mStatus == EDownloadStatus.COMPLETED) {
            return;
        }

        mStatus = EDownloadStatus.CANCELED;
    }
}
