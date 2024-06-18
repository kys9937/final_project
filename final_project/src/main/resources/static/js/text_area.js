var quill = new Quill('#editor-container', {
    theme: 'snow',
    modules: {
        toolbar: '#toolbar-container',
        imageResize: {
            displaySize: true,
            handleStyles: {
                backgroundColor: 'black',
                border: 'none',
                color: 'white',
                cursor: 'pointer',
                position: 'absolute',
                right: '0px',
                bottom: '0px',
                padding: '2px 8px',
                
            }
        }
    }
});

//editor-container를 누르면 p태그를 포커스 하는 코드
document.getElementById('editor-container').addEventListener('click', function() {
    const editorContainer = document.getElementById('editor-container');
    const paragraphs = editorContainer.querySelectorAll('p');
    if (paragraphs.length > 0) {
        const lastParagraph = paragraphs[paragraphs.length - 1];
        const range = document.createRange();
        const selection = window.getSelection();

        range.setStart(lastParagraph, 0);
        range.collapse(true);

        selection.removeAllRanges();
        selection.addRange(range);

        lastParagraph.focus();
    }
});

function submitForm() {
    var content = quill.root.innerHTML;
    document.getElementById('hidden-content').value = content;
    return true;
}
