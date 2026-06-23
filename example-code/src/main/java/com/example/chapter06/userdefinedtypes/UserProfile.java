package com.example.chapter06.userdefinedtypes;

public class UserProfile {
    private String mNickname;
    private ESubscriptionTier mTier;

    public UserProfile() {
        // Java의 멤버 변수 기본값을 보여주기 위해 의도적으로 값을 넣지 않는다.
    }

    public UserProfile(String nicknameOrNull) {
        mNickname = nicknameOrNull;
        mTier = ESubscriptionTier.FREE;
    }

    public UserProfile(String nicknameOrNull, ESubscriptionTier tierOrNull) {
        mNickname = nicknameOrNull;

        if (tierOrNull == null) {
            mTier = ESubscriptionTier.FREE;
        } else {
            mTier = tierOrNull;
        }
    }

    public String getNicknameOrNull() {
        return mNickname;
    }

    public ESubscriptionTier getTierOrNull() {
        return mTier;
    }

    public boolean changeNickname(String nicknameOrNull) {
        if (nicknameOrNull == null || nicknameOrNull.isBlank()) {
            return false;
        }

        mNickname = nicknameOrNull;
        return true;
    }

    public void upgradeToPro() {
        mTier = ESubscriptionTier.PRO;
    }

    public String buildSummary() {
        String displayNickname = mNickname;
        if (displayNickname == null) {
            displayNickname = "(설정되지 않음)";
        }

        String displayTier = "(설정되지 않음)";
        if (mTier != null) {
            displayTier = mTier.toString();
        }

        return "닉네임 = " + displayNickname + ", 등급 = " + displayTier;
    }
}
