/**
 * Created by Administrator on 2017/6/13.
 */

//布局
$("#splitter").kendoSplitter({
    panes: [ {
        max: "100%" ,
        size: "200px",
        collapsible: true,
        collapsedSize: "10%"
    }, {

    } ]
});

//视图
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

//绑定按钮
kendo.bind($('#toolbar-btn'), viewModel);

//绑定查询form
kendo.bind($('#query-form'), viewModel);

$('#query-form input').keydown(function (e) {
    if (e.keyCode == 13) {
        e.target.blur();
        viewModel.queryResource(e);
    }
});

//查询条件
function prepareQueryParameter(obj, options) {
    obj = obj || {};
    if (options) {
        obj.page = options.page;
        obj.pagesize = options.pageSize;
        if (options.sort && options.sort.length > 0) {
            obj.sortname = options.sort[0].field;
            obj.sortorder = options.sort[0].dir;
        }
    }
    for (var k in obj) {
        if (obj[k] === '' || obj[k] === null || obj[k] === undefined)
            delete obj[k]
        if (obj[k] instanceof Date) {
            obj[k] = obj[k].toJSON()
        }
    }
    return obj;
};

//dataSource
var crudServiceBaseUrl = '',
    dataSource = new kendo.data.DataSource({
        transport: {
            read: {
                url: "/queryParam",
                type: "POST",
                dataType: "json"
            },
            create: {
                url: "/create",
                contentType: "application/json",
                type: "POST"
            },
            update: {
                url: "/update",
                contentType: "application/json",
                type: "POST"
            },
            destroy: {
                url: "/delete",
                contentType: "application/json",
                type: "POST"
            },
            parameterMap: function (options, type) {
                if (type !== "read" && options.models) {
                    var datas = options.models;
                    return kendo.stringify(datas);
                } else if (type === "read") {
                    return prepareQueryParameter(viewModel.model.toJSON(), options);
                }
            }
        },
        batch: true,
        serverPaging: true,
        pageSize: 10,
        schema: {
            data: 'datas',
            total: 'total',
            model: {
                id: "key",
                fields: {
                    tableId: {type: "string"},
                    redisName: {type: "string"}
                }
            }
        }
    });

//grid
var grid = $("#grid").kendoGrid({
    dataSource: dataSource,
    navigatable: true,
    height: '100%',
    resizable: true,
    scrollable: true,
    columnsautoresize: true,
    columnsresize:true,
    selectable:'multiple,rowbox',
    toolbar: [
        { name: "create" },
        { name: "save" },
        { name: "custom" }
    ],
    pageable: {
        pageSizes: [5, 10, 20, 50],
        refresh: true,
        buttonCount: 5
    },
    columns: [

        {
            field: "tableId",
            title: 'tableId',
            width: "25%",
            headerAttributes: {
                "class": "table-header-cell",
                style: "text-align: center"
            },
            filterable: {
                multi:true
            },
            editor: function (container, options) {
                $('<input name="' + options.field + '"/>')
                    .appendTo(container)
                    .kendoTLEdit({
                        idField: 'tableId',
                        field: 'tableId',
                        dto: "com.hand.hmall.dto.hmallTable.HmallTable",
                        model: options.model
                    });
            }
        },
        {
            field: "redisName",
            title: 'redisName',
            width: "75%",
            headerAttributes: {
                "class": "table-header-cell",
                style: "text-align: center"
            },
            editor: function (container, options) {
                $('<input name="' + options.field + '"/>')
                    .appendTo(container)
                    .kendoTLEdit({
                        idField: 'tableId',
                        field: 'redisName',
                        dto: "com.hand.hmall.dto.hmallTable.HmallTable",
                        model: options.model
                    });
            }
        }
    ],
    editable: "inline"
}).data("kendoGrid");

//删除事件
function deleteData() {
    var checked = grid.selectedDataItems();
    if(grid.selectedDataItems().length){
        kendo.ui.showConfirmDialog({
            title:'查询信息',
            message:'删除确认！'
        }).done(function (event) {
            if (event.button == 'OK') {
                $.each(checked,function(i,v){
                    grid.dataSource.remove(v)
                })
                grid.dataSource.sync();
            }
        })
    }else{
        kendo.ui.showInfoDialog({
            message: '查询行'
        })
    }
}

//编辑
function editFunctionResources(){
    var checked = grid.selectedDataItems();
    if(checked.length == 1){
        var data = grid.dataItem(grid.select());
        grid.editRow(data);
    }else{
        kendo.ui.showInfoDialog({
            message: '选择行'
        })
    }
}
// Hap = {
//     version: '2.0',
//     defaultPrompt: {}
// };
// Hap.autoResizeGrid = function (grid_id) {
//     resizeGrid(grid_id);
//     $(window).resize(function () {
//         resizeGrid(grid_id);
//     });
// }
// /**
//  * outsizeGrid 表格随界面大小而变化
//  * @param grid_id
//  * 1.必须有个外层div
//  * 2.传入一个grid的id值
//  *
//  */
// function resizeGrid(grid_id) {
//     var grid = $(grid_id).data('kendoGrid');
//     if (grid) {
//         grid.autoResize();
//     }
// }
//自动根据当前屏幕大小调整表格
// Hap.autoResizeGrid("#grid");

