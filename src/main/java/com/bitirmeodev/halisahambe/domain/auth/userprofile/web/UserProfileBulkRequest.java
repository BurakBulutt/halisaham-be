package com.bitirmeodev.halisahambe.domain.auth.userprofile.web;

import java.util.List;

public record UserProfileBulkRequest(
        List<String> userIds
) {
}
