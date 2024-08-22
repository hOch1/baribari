// 전역 변수로 EventSource와 사용자 ID를 선언
let eventSource = null;

// 알림 카운트를 저장하는 함수
function saveNotificationCount(count) {
    localStorage.setItem('notificationCount', count);
}

// 알림 카운트를 복원하는 함수
function restoreNotificationCount() {
    const savedCount = localStorage.getItem('notificationCount');
    const badge = document.getElementById("notification-badge");

    if (savedCount !== null) {
        badge.innerText = savedCount;
        badge.style.display = 'inline';
    } else {
        badge.innerText = "0";
        badge.style.display = 'none';
    }
}

// 알림 카운트를 업데이트하고 저장하는 함수
function updateNotificationCount() {
    const badge = document.getElementById("notification-badge");
    let count = parseInt(badge.innerText);
    if (isNaN(count)) {
        count = 0;
    }
    count += 1;
    badge.innerText = count;
    badge.style.display = 'inline';
    saveNotificationCount(count); // 상태 저장
}

// EventSource를 초기화하는 함수
function initializeEventSource(userId) {
    if (eventSource) {
        eventSource.close(); // 기존 EventSource 연결 닫기
    }

    eventSource = new EventSource(`/notification?id=${userId}`, {
        headers: {
            'Accept': 'text/event-stream'
        }
    });

    eventSource.onmessage = function (event) {
        console.log("Received message:", event.data);
        updateNotificationCount(); // 알림 카운트 업데이트
    };

    eventSource.onerror = function (error) {
        console.log("SSE connection error:", error);
        eventSource.close();
        // 재연결 시도
        setTimeout(function () {
            initializeEventSource(userId);
        }, 5000); // 5초 후 재연결 시도
    };
}

// 페이지가 로드될 때 실행되는 함수
document.addEventListener('DOMContentLoaded', function () {
    if (userId !== 'null') {
        restoreNotificationCount(); // 알림 상태 복원
        initializeEventSource(userId); // EventSource 초기화
    }

    document.getElementById("notification-link").addEventListener("click", function () {
        const badge = document.getElementById("notification-badge");
        badge.innerText = "0";
        badge.style.display = 'none'; // 배지 숨기기
        saveNotificationCount(0); // 상태 저장
        alert("알림을 확인했습니다."); // 클릭 시 알림 처리
    });
});
