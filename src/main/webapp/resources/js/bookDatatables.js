$(function(){
    $("#books").dataTable({
        serverSide: false,
        processing: true,
        paging: true,
        sort: "position",
        pagingType: "full_numbers",
        pageLength: 10,
        displayStart: 0,
        columns: [
            { data: "author" },
            { data: "title" },
            { data: "year" },
            {
                targets: [3],
                orderable: false,
                searchable: false,
                visible: true
            },
            {
                targets: [4],
                orderable: false,
                searchable: false,
                visible: access
            },
            {
                targets: [5],
                orderable: false,
                searchable: false,
                visible: access
            }
        ],
        order: [[0, "asc"]]
    });
});