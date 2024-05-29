<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

    <!-- ������Ʈ ��û�ϱ� -->
    <div id="r_apply">
        <div class="r_apply_window">
            <!-- ������ ���� -->
            <div class="r_apply_title">
                <h2>��û�ϱ�</h2>
                <!-- â �ݱ� -->
                <div class="r_apply_close">X</div>
            </div>
            <!-- ���� -->
            <div class="content">
                <!-- ��û�ϱ� DIV -->
                <div class="ra_content_div">
                    <h4>������Ʈ ��û�ϱ�</h4>
                    <form id="ra_form" action="apply.do" method="post">
                    	<input type="hidden" name="rb_num" value="${rboard.rb_num}">
                    	<input type="hidden" name="rb_title" value="${rboard.rb_title}">
                        <textarea id="ra_content" name="ra_content"></textarea>
                        <!-- ��ư -->
		                <div class="btn-div">
		                    <input type="submit" id="apply_btn" value="��û" class="btn" >
		                    <div class="r_apply_close btn" id="close_btn">
		                        <span>���</span>
		                    </div>
		                </div>
                    </form>
                </div>
                
            </div>
        </div>
    </div>


    <script>
        const r_apply = document.getElementById("r_apply");

        function modalOn() {
        	const background = document.getElementById("modal_background");
        	background.classList.add("modal-on-background");
        	r_apply.style.display = "flex";
        }

        function isModalOn() {
            return r_apply.style.display === "flex";
        }

        function modalOff() {
        	const background = document.getElementById("modal_background");
        	background.classList.remove("modal-on-background");
        	r_apply.style.display = "none";
        }

        const btnModal = document.getElementById("btn-modal");

        btnModal.addEventListener("click", e => {
            modalOn();
        });

        // ù ��° �ݱ� ��ư
        document.querySelector(".r_apply_close").addEventListener("click", e => {
            modalOff();
        });

        // ������ �ݱ� ��ư�� ���� �̺�Ʈ ������ �߰�
        document.getElementById("close_btn").addEventListener("click", e => {
            modalOff();
        });
    </script>