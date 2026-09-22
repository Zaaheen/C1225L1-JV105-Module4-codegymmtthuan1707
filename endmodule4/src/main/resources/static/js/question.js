$(function () {
    const detailModal = new bootstrap.Modal(document.getElementById('detailModal'));
    const deleteModal = new bootstrap.Modal(document.getElementById('deleteModal'));

    function loadDetail(id) {
        $('#detailLoading').removeClass('d-none');
        $('#detailContent').addClass('d-none');
        $('#detailError').addClass('d-none');
        detailModal.show();
        $.ajax({
            url: '/questions/' + id + '/detail',
            method: 'GET',
            dataType: 'json'
        }).done(function (data) {
            $('#detailTitle').text(data.title || 'N/A');
            $('#detailType').text(data.questionTypeName || 'N/A');
            $('#detailDate').text(data.dateCreate || 'N/A');
            $('#detailStatus').text(data.status === 'PENDING' ? 'Chờ phản hồi' : 'Đã phản hồi');
            $('#detailQuestion').text(data.content || 'N/A');
            $('#detailAnswer').text(data.answer || 'N/A');
            $('#detailAnsweredBy').text(data.answeredBy || 'N/A');
            $('#detailLoading').addClass('d-none');
            $('#detailContent').removeClass('d-none');
        }).fail(function (xhr) {
            const message = xhr.responseJSON && xhr.responseJSON.message
                ? xhr.responseJSON.message
                : 'Không thể tải chi tiết câu hỏi. Vui lòng thử lại.';
            $('#detailLoading').addClass('d-none');
            $('#detailError').text(message).removeClass('d-none');
        });
    }

    $('.question-row').on('click', function (event) {
        if ($(event.target).closest('a, button, form').length) return;
        loadDetail($(this).data('question-id'));
    });

    $('.detail-trigger').on('click', function (event) {
        event.stopPropagation();
        loadDetail($(this).closest('tr').data('question-id'));
    });

    $('.delete-trigger').on('click', function (event) {
        event.stopPropagation();
        $('#deleteTitle').text($(this).data('title'));
        $('#deleteForm').attr('action', '/questions/delete/' + $(this).data('id'));
        deleteModal.show();
    });
});
