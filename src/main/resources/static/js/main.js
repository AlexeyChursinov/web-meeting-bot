$(document).ready(function() {
    $('#answers').DataTable({
        language: {
            url: '/MeetingWeb/static/js/vendor/jquery.dataTables.ru.json'
        },
        "order": [[ 0, "desc" ]],
        columnDefs: [{
            targets: 0,
            render: DataTable.render.moment('DD.MM.YYYY', 'DD.MM.YYYY')
        }]
    });
});