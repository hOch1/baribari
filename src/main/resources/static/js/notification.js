if (userId != null) {
    const eventSource = new EventSource(`/notification?id=${userId}`);

    eventSource.onmessage = function (event) {
        console.log(event.data);

        const badge = document.getElementById("notification-badge");
        let count = parseInt(badge.innerText);
        if (isNaN(count)) {
            count = 0;
        }
        badge.innerText = count + 1;
        badge.style.display = 'inline'; // 배지를 표시
    }


    eventSource.onerror = function (error) {
        console.log(error);
        eventSource.close();
    }

    document.getElementById("notification-link").addEventListener("click", function () {
        const badge = document.getElementById("notification-badge");
        badge.innerText = "0";
        badge.style.display = 'none'; // 배지 숨기기
        alert("알림을 확인했습니다."); // 예시: 클릭 시 알림 처리
    });
}