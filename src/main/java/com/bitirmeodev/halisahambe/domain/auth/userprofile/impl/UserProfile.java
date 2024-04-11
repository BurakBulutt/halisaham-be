package com.bitirmeodev.halisahambe.domain.auth.userprofile.impl;

import com.bitirmeodev.halisahambe.library.util.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = UserProfile.TABLE)
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserProfile extends AbstractEntity {
    public static final String TABLE = "user_profile";
    private static final String COL_USER_ID = "user_id";
    private static final String COL_PHOTO = "photo";

    @Column(name = COL_USER_ID,nullable = false)
    private String userId;

    @Column(name = COL_PHOTO)
    @Lob
    private byte[] photo;
}
