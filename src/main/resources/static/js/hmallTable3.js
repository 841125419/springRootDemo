/**
 * Created by Administrator on 2017/6/14.
 */
// 竖直方向
$('#vertical').kendoSplitter({
    orientation: "horizontal",
    panes : [
        { collapsible: true, size: '50px'},
        { collapsible: false }
    ]
});

//回车事件
$('#oper-form').keydown(function (e) {
    if (e.keyCode == 13) {
        e.target.blur();
        viewModel.queryResource(e);
    }
});

//分页信息定制
var pageable ={
    pageSizes: true,
    input: true,
    refresh: true,
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
}

//1、定义一个viewModel
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

//头表信息
var mainDataSource = new kendo.data.DataSource({
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
        ,
        parameterMap: function(options, operation) {
            if (operation !== "read" && options.models) {
                return kendo.stringify(options.models);
            } else if (operation === "read") {
                viewModel.model.page = options.page;
                viewModel.model.pageSize = options.pageSize;
                return viewModel.model.toJSON();
            }
            return JSON.parse(kendo.stringify(options));
        }
    },
    pageSize: 10,
    serverPaging: true,
    serverSorting: false,//false ： 浏览器排序生效  true ： 浏览器排序失效
    requestEnd: function(e) {
        // var response = e.response;
        // if(response){
        //     var type = e.type;
        //     if(type !='read'){
        //         var status = response.status;
        //         if(status == 200){
        //             lert(response.message);
        //             this.read();
        //         } else {
        //             alert(response.message);
        //         }
        //     }
        // }else{
        //     alert("服务器异常，请重试！");
        // }

    },
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
});


var mainGrid = $("#grid").kendoGrid({
    dataSource: mainDataSource,
    toolbar: kendo.template($("#template").html()),
    columnsautoresize: true,
    columnsresize:true,
    columnMenu: true,
    resizable: true,
    filterable: true,
    height: 650,
    scrollable: true,
    sortable: true,
    batch: true,
    pageable: pageable,
    detailInit: detailInit,
    // dataBound: function() {
    //     this.expandRow(this.tbody.find("tr.k-master-row").first());
    // },
    columns: [
        { field: "tableId", title: "tableId", width: "110px" },
        { field: "redisName", title: "redisName", width: "110px" },
        { field: "oracleName", title: "oracleName", width: "110px" },
        { field: "refService", title: "refService", width: "110px" },
        { field: "tableName", width: "110px" },
        { field: "tableDesc", width: "110px" },
        { field: "comments", width: "110px" },
        { field: "tableType", width: "110px" },
        { command: ["edit", "destroy"], title: "&nbsp;", width: "170px" }
    ],
    editable: "inline"
}).data("kendoGrid");


function detailInit(e) {

//详情信息
    var subDataSource = new kendo.data.DataSource({
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
                    tableId: {type: "string", validation: { required: true } },
                    columnName: {type: "string"},
                    columnType: {type: "string"},
                    redisType: {type: "string"},
                    keyFlag: {type: "string"},
                    comments: {type: "string"}
                }
            }
        }
    });


    $("<div/>").appendTo(e.detailCell).kendoGrid({
        dataSource: subDataSource,
        toolbar: [{name:"create",text:"新建"}],
        columnsautoresize: true,
        columnsresize:true,
        columnMenu: true,
        resizable: true,
        scrollable: false,
        // sortable: true,
        batch: true,
        pageable: pageable,
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

//绑定查询框
kendo.bind($('#oper-form'), viewModel);

