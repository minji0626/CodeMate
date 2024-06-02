$(document).ready(function() {
	const calendar = $(".calendar"),
		date = $(".date"),
		daysContainer = $(".days"),
		prev = $(".prev"),
		next = $(".next"),
		todayBtn = $(".today-btn"),
		gotoBtn = $(".goto-btn"),
		dateInput = $(".date-input"),
		eventDay = $(".event-day"),
		eventDate = $(".event-date"),
		eventsContainer = $(".events"),
		addEventBtn = $(".add-event"),
		addEventWrapper = $(".add-event-wrapper "),
		addEventCloseBtn = $(".close "),
		addEventTitle = $(".event-name "),
		addEventFrom = $(".event-time-from "),
		addEventTo = $(".event-time-to "),
		addEventSubmit = $(".add-event-btn ");

	let today = new Date();
	let activeDay;
	let month = today.getMonth();
	let year = today.getFullYear();

	const months = [
		"1월",
		"2월",
		"3월",
		"4월",
		"5월",
		"6월",
		"7월",
		"8월",
		"9월",
		"10월",
		"11월",
		"12월"
	];

	const eventsArr = [];

	function initCalendar() {
		const firstDay = new Date(year, month, 1);
		const lastDay = new Date(year, month + 1, 0);
		const prevLastDay = new Date(year, month, 0);
		const prevDays = prevLastDay.getDate();
		const lastDate = lastDay.getDate();
		const day = firstDay.getDay();
		const nextDays = 7 - lastDay.getDay() - 1;

		date.html(year + "년 " + months[month]);

		let days = "";

		for (let x = day; x > 0; x--) {
			days += `<div class="day prev-date">${prevDays - x + 1}</div>`;
		}

		for (let i = 1; i <= lastDate; i++) {
			let event = false;
			eventsArr.forEach((eventObj) => {
				if (
					eventObj.day === i &&
					eventObj.month === month + 1 &&
					eventObj.year === year
				) {
					event = true;
				}
			});
			if (
				i === new Date().getDate() &&
				year === new Date().getFullYear() &&
				month === new Date().getMonth()
			) {
				activeDay = i;
				getActiveDay(i);
				updateEvents(i);
				if (event) {
					days += `<div class="day today active event">${i}</div>`;
				} else {
					days += `<div class="day today active">${i}</div>`;
				}
			} else {
				if (event) {
					days += `<div class="day event">${i}</div>`;
				} else {
					days += `<div class="day ">${i}</div>`;
				}
			}
		}

		for (let j = 1; j <= nextDays; j++) {
			days += `<div class="day next-date">${j}</div>`;
		}
		daysContainer.html(days);
		addListner();
	}

	function prevMonth() {
		month--;
		if (month < 0) {
			month = 11;
			year--;
		}
		initCalendar();
	}

	function nextMonth() {
		month++;
		if (month > 11) {
			month = 0;
			year++;
		}
		initCalendar();
	}

	prev.on("click", prevMonth);
	next.on("click", nextMonth);

	initCalendar();

	function addListner() {
		const days = $(".day");
		days.each(function() {
			$(this).on("click", function(e) {
				getActiveDay($(this).text());
				updateEvents(Number($(this).text()));
				activeDay = Number($(this).text());
				days.removeClass("active");
				if ($(this).hasClass("prev-date")) {
					prevMonth();
					setTimeout(() => {
						$(".day").each(function() {
							if (!$(this).hasClass("prev-date") && $(this).text() === e.target.innerHTML) {
								$(this).addClass("active");
							}
						});
					}, 100);
				} else if ($(this).hasClass("next-date")) {
					nextMonth();
					setTimeout(() => {
						$(".day").each(function() {
							if (!$(this).hasClass("next-date") && $(this).text() === e.target.innerHTML) {
								$(this).addClass("active");
							}
						});
					}, 100);
				} else {
					$(this).addClass("active");
				}
			});
		});
	}

	todayBtn.on("click", () => {
		today = new Date();
		month = today.getMonth();
		year = today.getFullYear();
		initCalendar();
	});

	dateInput.on("input", (e) => {
		dateInput.val(dateInput.val().replace(/[^0-9/]/g, ""));
		if (dateInput.val().length === 2) {
			dateInput.val(dateInput.val() + "/");
		}
		if (dateInput.val().length > 7) {
			dateInput.val(dateInput.val().slice(0, 7));
		}
		if (e.inputType === "deleteContentBackward" && dateInput.val().length === 3) {
			dateInput.val(dateInput.val().slice(0, 2));
		}
	});

	gotoBtn.on("click", gotoDate);

	function gotoDate() {
		const dateArr = dateInput.val().split("/");
		if (dateArr.length === 2 && dateArr[0] > 0 && dateArr[0] < 13 && dateArr[1].length === 4) {
			month = dateArr[0] - 1;
			year = dateArr[1];
			initCalendar();
		} else {
			alert("유효하지 않은 날짜 형식입니다.");
		}
	}

	function getActiveDay(date) {
		const day = new Date(year, month, date);
		const dayName = getKoreanDay(day.getDay());
		eventDay.html(dayName);
		eventDate.html(`${year}년 ${months[month]} ${date}일`);
	}

	function getKoreanDay(day) {
		const daysOfWeek = ["일요일", "월요일", "화요일", "수요일", "목요일", "금요일", "토요일"];
		return daysOfWeek[day];
	}

	$(".add-event").on("click", function() {
		$(".add-event-wrapper").toggleClass("active");
	});

	$(".close").on("click", function() {
		$(".add-event-wrapper").removeClass("active");
	});

	$(document).on("click", function(e) {
		if (e.target !== $(".add-event")[0] && !$.contains($(".add-event-wrapper")[0], e.target)) {
			$(".add-event-wrapper").removeClass("active");
		}
	});

	const team_num = sessionStorage.getItem("team_num");

	$(".add-event-btn").on("click", function() {
		const eventTitle = $(".event-name").val();
		const eventTimeFrom = $(".event-time-from").val().trim();
		const eventTimeTo = $(".event-time-to").val().trim();

		if (eventTitle === "") {
			alert("To-Do 내용을 작성하세요");
			return;
		}

		const timeFrom = eventTimeFrom || " ";
		const timeTo = eventTimeTo || " ";

		if (eventTimeFrom !== "" && eventTimeTo !== "") {
			const timeFromArr = eventTimeFrom.split(":");
			const timeToArr = eventTimeTo.split(":");
			if (timeFromArr.length !== 2 || timeToArr.length !== 2 ||
				parseInt(timeFromArr[0]) < 0 || parseInt(timeFromArr[0]) > 23 ||
				parseInt(timeFromArr[1]) < 0 || parseInt(timeFromArr[1]) > 59 ||
				parseInt(timeToArr[0]) < 0 || parseInt(timeToArr[0]) > 23 ||
				parseInt(timeToArr[1]) < 0 || parseInt(timeToArr[1]) > 59) {
				alert("잘못된 시간 형식입니다.");
				return;
			}
		}

		$.ajax({
			type: "post",
			url: "AddTeam_Todo.do",
			data: {
				team_num: team_num,
				tt_content: eventTitle,
				tt_date: `${year}-${month + 1}-${activeDay}`,
				tt_start: timeFrom,
				tt_end: timeTo
			},
			success: function(param) {
				if (param.result === "success") {
					alert("이벤트가 추가되었습니다.");
				} else if (param.result === "logout") {
					alert("로그인 후 사용해주세요.");
				} else {
					alert("오류가 발생하였습니다.");
				}
			},
			error: function() {
				alert("네트워크 오류가 발생하였습니다.");
			}
		});
	});

	function updateEvents(date) {
		$.ajax({
			type: 'post',
			url: 'getTeamTodoList.do',
			data: {
				team_num: sessionStorage.getItem("team_num"),
				tt_date: `${year}-${month + 1}-${date}`
			},
			dataType: 'json',
			success: function(param) {
				let events = "";
				const eventsArr = param.teamtodo; // 서버에서 받은 이벤트 배열

				eventsArr.forEach((event) => {
					events += `<div class="event">
						<div class="title">
							<h3 class="event-title">${event.title}</h3>
						</div>
						<div class="event-time">
							<span class="event-time">${event.time}</span>
						</div>
						<div class="event-buttons">
							<button class="del-btn" data-event-id="${event.id}">삭제</button>
						</div>
					</div>`;
				});

				if (events === "") {
					events = `<div class="no-event">
						<h3>예정된 이벤트 없음</h3>
					</div>`;
				}

				eventsContainer.html(events); // 이벤트 컨테이너 업데이트
			},
			error: function() {
				alert("이벤트를 불러오는 데 실패했습니다.");
			}
		});
	}

});


