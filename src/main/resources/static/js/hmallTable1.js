/**
 * Created by Administrator on 2017/6/13.
 */
$(document).ready(function() {
    var viewModel = kendo.observable({
        model: {},
        createFunction: function(){
            $('#grid').data('kendoGrid').addRow();
        },
        saveFunction: function () {
            $('#grid').data('kendoGrid').saveChanges();
        },
        queryResource: function (e) {
            $('#grid').data('kendoGrid').dataSource.page(1);
        }
    });

    var element = $("#grid").kendoGrid({
        dataSource: {
            transport: {
                read: {
                    url: "/hmallTable/queryParam",
                    type: "POST",
                    dataType: "json"
                },
                update: {
                    url: "/hmallTable/update",
                    type: "POST",
                    dataType: "json",
                    complete: function(e) {
                        $("#grid").data("kendoGrid").dataSource.read();
                    }
                },
                destroy: {
                    url: "/hmallTable/delete",
                    type: "POST",
                    dataType: "json",
                    complete: function(e) {
                        $("#grid").data("kendoGrid").dataSource.read();
                    }
                },
                create: {
                    url: "/hmallTable/create",
                    type: "POST",
                    dataType: "json",
                    complete: function(e) {
                        $("#grid").data("kendoGrid").dataSource.read();
                    }
                }
                // ,
                // parameterMap: function(options, operation) {
                //     if (operation !== "read" && options.models) {
                //         return {models: kendo.stringify(options.models)};
                //     }
                // }
            },
            pageSize: 10,
            serverPaging: true,
            serverSorting: true,
            schema: {
                data: 'datas',
                total: 'total',
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
            }
        },
        toolbar: ["create"],
        columnsautoresize: true,
        columnsresize:true,
        height: 600,
        sortable: true,
        pageable: {
        pageSizes: true,
            input: true,
            buttonCount: 5,
            messages: {
            display: "显示{0}-{1}条，共{2}条",
                empty: "没有数据",
                page: "页",
                of: "/ {0}",
                itemsPerPage: "条/页",
                first: "第一页",
                previous: "前一页",
                next: "下一页",
                last: "最后一页",
                refresh: "刷新"
            }
        },
        detailInit: detailInit,
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
            { command: ["edit", "destroy"], title: "&nbsp;", width: "250px" }
        ],
        editable: "inline"
    });
});

function detailInit(e) {
    $("<div/>").appendTo(e.detailCell).kendoGrid({
        dataSource: {
            transport: {
                read: {
                    url: "/hmallTableColumn/query",
                    type: "POST",
                    dataType: "json"
                },
                update: {
                    url: "/hmallTableColumn/update",
                    type: "POST",
                    dataType: "json",
                    complete: function(e) {
                        $("#grid").data("kendoGrid").dataSource.read();
                    }
                },
                destroy: {
                    url: "/hmallTableColumn/delete",
                    type: "POST",
                    dataType: "json",
                    complete: function(e) {
                        $("#grid").data("kendoGrid").dataSource.read();
                    }
                },
                create: {
                    url: "/hmallTableColumn/create",
                    type: "POST",
                    dataType: "json",
                    complete: function(e) {
                        $("#grid").data("kendoGrid").dataSource.read();
                    }
                }
                // ,
                // parameterMap: function(options, operation) {
                //     if (operation !== "read" && options.models) {
                //         return {models: kendo.stringify(options.models)};
                //     }
                // }
            },
            serverPaging: true,
            serverSorting: true,
            serverFiltering: true,
            pageSize: 10,
            filter: { field: "columnId", operator: "eq", value: e.data.tableId },
            schema: {
                data: 'datas',
                total: 'total',
                model: {
                    id: "columnId",
                    fields: {
                        columnId: {type: "number",editable: false, nullable: true},
                        tableId: {type: "string"},
                        columnName: {type: "string"},
                        columnType: {type: "string"},
                        redisType: {type: "string"},
                        keyFlag: {type: "string"},
                        comments: {type: "string"}
                    }
                }
            }
        },
        toolbar: ["create"],
        columnsautoresize: true,
        columnsresize:true,
        scrollable: false,
        sortable: true,
        pageable: {
            pageSizes: true,
            input: true,
            buttonCount: 5,
            messages: {
                display: "显示{0}-{1}条，共{2}条",
                empty: "没有数据",
                page: "页",
                of: "/ {0}",
                itemsPerPage: "条/页",
                first: "第一页",
                previous: "前一页",
                next: "下一页",
                last: "最后一页",
                refresh: "刷新"
            }
        },
        columns: [
            { field: "columnId", width: "110px", hidden: false,},
            { field: "tableId", title:"tableId", width: "110px", hidden: false,},
            { field: "columnName", title:"columnName", width: "110px" },
            { field: "columnType", title: "columnType", width: "110px" },
            { field: "columnDesc", title: "columnDesc", width: "110px" },
            { field: "redisType", title: "redisType", width: "110px" },
            { field: "keyFlag", title: "keyFlag", width: "110px" },
            { field: "comments", title: "comments", width: "110px" },
            { command: [{name: "edit",
                click: function(e) {
                    // prevent page scroll position change
                    e.preventDefault();
                    // e.target is the DOM element representing the button
                    var tr = $(e.target).closest("tr"); // get the current table row (tr)
                    // get the data bound to the current table row
                    var data = this.dataItem(tr);
                    //console.log("Details for: " + data.name);
                }}, "destroy"], title: "&nbsp;", width: "250px" }
        ],
        editable: "inline"
    });
}

function customBoolEditor(container, options) {
    $('<input class="k-checkbox" type="checkbox" name="Discontinued" data-type="boolean" data-bind="checked:Discontinued">').appendTo(container);
    $('<label class="k-checkbox-label">&#8203;</label>').appendTo(container);
}