// script.js

// 커맨드 실행 함수
function execCmd(command) {
    if (command === 'createLink') {
        let url = prompt('Enter the link here: ', 'http:\/\/');
        document.execCommand(command, false, url);
    } else {
        document.execCommand(command, false, null);
    }
}

// 이미지 삽입 함수
function insertImage(event) {
    const file = event.target.files[0];
    if (file) {
        const reader = new FileReader();
        reader.onload = function(e) {
            const img = document.createElement('img');
            img.src = e.target.result;
            img.style.maxWidth = '100%';
            insertHTMLAtCursor(img.outerHTML);
        };
        reader.readAsDataURL(file);
    }
}

// 커서 위치에 HTML 삽입 함수
function insertHTMLAtCursor(html) {
    const sel = window.getSelection();
    if (sel.rangeCount > 0) {
        const range = sel.getRangeAt(0);
        range.deleteContents();
        const div = document.createElement("div");
        div.innerHTML = html;
        const frag = document.createDocumentFragment();
        let node, lastNode;
        while ((node = div.firstChild)) {
            lastNode = frag.appendChild(node);
        }
        range.insertNode(frag);
        
        // 커서를 삽입된 내용의 끝으로 이동
        if (lastNode) {
            range.setStartAfter(lastNode);
            range.collapse(true);
            sel.removeAllRanges();
            sel.addRange(range);
        }
    } else {
        console.error("No selection range found.");
    }
}

//버튼 스타일 변경 함수
function toggleStyle(btnId, style) {
    var btn = document.getElementById(btnId);
    var isActive = btn.classList.contains('active');
    if (isActive) {
        document.execCommand('removeFormat', false, style);
        btn.style.backgroundColor = '';
        btn.classList.remove('active');
    } else {
        document.execCommand(style, false, null);
        btn.style.backgroundColor = 'lightgray'; // 활성화 시 배경색 변경
        btn.classList.add('active');
    }
}
