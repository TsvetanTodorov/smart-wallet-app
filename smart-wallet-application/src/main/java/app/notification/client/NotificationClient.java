package app.notification.client;

import app.notification.client.dto.UpsertNotificationPreference;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "notification-svc", url = "https://localhost:8082/api/v1/notifications")
public interface NotificationClient {


    @PostMapping("/preferences")
    ResponseEntity<Void> upsertNotificationPreference(@RequestBody UpsertNotificationPreference notificationPreference);

}
