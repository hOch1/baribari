if (!window.notificationInitialized) {

    function saveNotificationCount(count) {
        localStorage.setItem('notificationCount', count);
    }

    function restoreNotificationCount() {
        const savedCount = localStorage.getItem('notificationCount');
        const badge = document.getElementById("notification-badge");

        if (savedCount !== null && parseInt(savedCount) > 0) {
            badge.innerText = savedCount;
            badge.style.display = 'inline';
        } else {
            badge.innerText = "0";
            badge.style.display = 'none';
        }
    }

    function updateNotificationCount() {
        const badge = document.getElementById("notification-badge");
        let count = parseInt(badge.innerText);
        if (isNaN(count)) {
            count = 0;
        }
        count += 1;
        badge.innerText = count.toString();
        badge.style.display = 'inline';
        saveNotificationCount(count);
    }

    function saveNotification(notification) {
        let notifications = getStoredNotifications();
        notifications.unshift(notification); // Add new notifications to the start
        localStorage.setItem('notifications', JSON.stringify(notifications));
    }

    function getStoredNotifications() {
        const storedNotifications = localStorage.getItem('notifications');
        return storedNotifications ? JSON.parse(storedNotifications) : [];
    }

    function deleteNotification(index) {
        let notifications = getStoredNotifications();
        notifications.splice(index, 1); // Remove notification at the given index
        localStorage.setItem('notifications', JSON.stringify(notifications));
    }

    function initializeEventSource() {
        if (window.eventSource) {
            console.log("EventSource is already initialized.");
            return;
        }

        if (userId === 'null' || !userId) {
            console.error("Invalid user ID. SSE will not be initialized.");
            return;
        }

        window.eventSource = new EventSource(`/notification?id=${userId}`);

        window.eventSource.onmessage = function (event) {
            console.log("Received message:", event.data);

            if (event.data !== "ping") {
                const data = JSON.parse(event.data); // Parse the incoming data
                const message = data.message;
                const link = data.link;

                updateNotificationCount();
                saveNotification({ message, link }); // Save the notification with message and link
            }
        };

        window.eventSource.onerror = function (error) {
            console.log("SSE connection error:", error);
            window.eventSource.close();
            window.eventSource = null;
            setTimeout(initializeEventSource, 5000);
        };
    }

    document.addEventListener('DOMContentLoaded', function () {
        restoreNotificationCount();
        initializeEventSource();

        document.getElementById("notificationDropdown").addEventListener("click", function () {
            const badge = document.getElementById("notification-badge");
            const notificationList = document.getElementById("notification-list");

            // Clear the notification list
            notificationList.innerHTML = "";

            // Get notifications from localStorage
            const notifications = getStoredNotifications();
            if (notifications.length > 0) {
                notifications.forEach((notification, index) => {
                    const li = document.createElement("li");
                    li.className = "dropdown-item";
                    li.innerHTML = `<a href="${notification.link}" data-index="${index}">${notification.message}</a>`;
                    notificationList.appendChild(li);

                    // Add click event to delete the notification when clicked
                    li.addEventListener('click', function () {
                        const notificationIndex = this.querySelector('a').getAttribute('data-index');
                        deleteNotification(notificationIndex); // Delete the clicked notification
                        this.remove(); // Remove the notification from the list

                        // Update badge count
                        const newCount = parseInt(badge.innerText) - 1;
                        badge.innerText = newCount.toString();
                        if (newCount === 0) {
                            badge.style.display = 'none';
                        }
                        saveNotificationCount(newCount);
                    });
                });
            } else {
                notificationList.innerHTML = "<li class='dropdown-item'>No new notifications.</li>";
            }

            // Toggle the notification list display
            const isDropdownVisible = notificationList.style.display === "block";
            notificationList.style.display = isDropdownVisible ? "none" : "block";
        });
    });

    window.notificationInitialized = true;
}

