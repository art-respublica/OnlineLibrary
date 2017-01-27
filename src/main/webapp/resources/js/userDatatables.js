$(function(){
    $("#users").dataTable({
        serverSide: false,
        processing: true,
        paging: true,
        sort: "position",
        pagingType: "full_numbers",
        pageLength: 10,
        displayStart: 0,
        columns: [
            { data: "name" },
            { data: "email" },
            { data: "registered" },
            { data: "enabled" },
            { data: "roles" },
            {
                orderable: false,
                searchable: false,
                visible: true
            },
            {
                orderable: false,
                searchable: false,
                visible: true
            }
        ],
        order: [[0, "asc"]]
    });
});