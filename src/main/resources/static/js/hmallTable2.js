$(document).ready(function () {
    var crudServiceBaseUrl = "",
        htDataSource = new kendo.data.DataSource({
            transport: {
                read:  {
                    url: crudServiceBaseUrl + "/hmallTable/queryParam",
                    dataType: "json",
                    type: "post"
                },
                update: {
                    url: crudServiceBaseUrl + "/hmallTable/update",
                    dataType: "json",
                    type: "post"
                },
                destroy: {
                    url: crudServiceBaseUrl + "/hmallTable/delete",
                    dataType: "json",
                    type: "post"
                },
                create: {
                    url: crudServiceBaseUrl + "/hmallTable/create",
                    dataType: "json",
                    type: "post"
                },
                parameterMap: function(options, operation) {
                    if (operation !== "read" && options.models) {
                        return kendo.stringify(options.models);
                    }
                }
            },
            batch: true,
            pageSize: 20,
            schema: {
                data: "datas",
                total: "total",
                model: {
                    id: "tableId",
                    fields: {
                        tableId: {type: "number",editable: false, nullable: true},
                        redisName: {type: "string", validation: { required: true } },
                        oracleName: {type: "string"},
                        refService: {type: "string"},
                        tableName: {type: "string"},
                        tableDesc: {type: "string"},
                        comments: {type: "string"},
                        tableType: {type: "string"}
                    }
                }
            },
            error: function(e) {
                alert(e.errors); // displays "Invalid query"
            }
        });

    $("#grid").kendoGrid({
        dataSource: htDataSource,
        pageable: true,
        height: 550,
        toolbar: ["create"],
        //detailInit: detailInit,
        dataBound: function() {
            this.expandRow(this.tbody.find("tr.k-master-row").first());
        },
        columns: [
            { field: "tableId", title: "tableId", width: "110px" },
            { field: "redisName", title: "redisName", width: "110px" },
            { field: "oracleName", title: "oracleName", width: "110px" },
            { field: "refService", title: "refService", width: "110px" },
            { field: "tableName", width: "110px" },
            { field: "tableDesc", width: "110px" },
            { field: "comments", width: "110px" },
            { field: "tableType", width: "110px" },
            { command: ["edit", "destroy"], title: " ", width: "250px" }],
        editable: "popup"
    });
});