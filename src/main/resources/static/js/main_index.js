//define data array
/*var tabledata = [
    {id:1, name:"Oli Bob", progress:12, gender:"male", rating:1, col:"red", dob:"19/02/1984", car:1},
    {id:2, name:"Mary May", progress:1, gender:"female", rating:2, col:"blue", dob:"14/05/1982", car:true},
    {id:3, name:"Christine Lobowski", progress:42, gender:"female", rating:0, col:"green", dob:"22/05/1982", car:"true"},
    {id:4, name:"Brendon Philips", progress:100, gender:"male", rating:1, col:"orange", dob:"01/08/1980"},
    {id:5, name:"Margret Marmajuke", progress:16, gender:"female", rating:5, col:"yellow", dob:"31/01/1999"},
    {id:6, name:"Frank Harbours", progress:38, gender:"male", rating:4, col:"red", dob:"12/05/1966", car:1},
];

/!*!//initialize table
var table = new Tabulator("#example-table", {
    data:tabledata, //assign data to table
    autoColumns:true, //create columns from data field names
});*!/

var table2 = new Tabulator("#example-table", {
    data:tabledata,           //load row data from array
    layout:"fitColumns",      //fit columns to width of table
    responsiveLayout:"hide",  //hide columns that dont fit on the table
    addRowPos:"top",          //when adding a new row, add it to the top of the table
    history:true,             //allow undo and redo actions on the table
    pagination:"local",       //paginate the data
    paginationSize:7,         //allow 7 rows per page of data
    paginationCounter:"rows", //display count of paginated rows in footer
    movableColumns:true,      //allow column order to be changed
    initialSort:[             //set the initial sort order of the data
        {column:"name", dir:"asc"},
    ],
    columnDefaults:{
        tooltip:true,         //show tool tips on cells
    },
    columns:[                 //define the table columns
        {title:"Name", field:"name", editor:"input"},
        {title:"Task Progress", field:"progress", hozAlign:"left", formatter:"progress", editor:true},
        {title:"Gender", field:"gender", width:95, editor:"select", editorParams:{values:["male", "female"]}},
        {title:"Rating", field:"rating", formatter:"star", hozAlign:"center", width:100, editor:true},
        {title:"Color", field:"col", width:130, editor:"input"},
        {title:"Date Of Birth", field:"dob", width:130, sorter:"date", hozAlign:"center"},
        {title:"Driver", field:"car", width:90,  hozAlign:"center", formatter:"tickCross", sorter:"boolean", editor:true},
    ],
});*/

let col = [
    {title: "id", field: "id", visible: false, hozAlign: "left", vertAlign: "middle", visible: false,},
    {title: "전자결재KEY", field: "ifkey", hozAlign: "left", vertAlign: "middle", width: 130, visible: false,},
    {title: "User", field: "user", hozAlign: "left", vertAlign: "middle", width: 130, visible: false,},
    {title: "Ukid", field: "ukid", visible: false, hozAlign: "left", vertAlign: "middle", width: 130, visible: false,},
    {title: "원장유형", field: "lt", hozAlign: "left", vertAlign: "middle", width: 130, visible: false,},
    {title: "배치유형", field: "icut", hozAlign: "left", vertAlign: "middle", width: 130, },
    {title: "배치유형 명", field: "dl10", hozAlign: "left", vertAlign: "middle", width: 130, },
    {title: "배치번호", field: "icu", hozAlign: "left", vertAlign: "middle", width: 130, },
    {title: "작성회사", field: "kco", hozAlign: "left", vertAlign: "middle", width: 130, },
    {title: "문서유형", field: "dct", hozAlign: "left", vertAlign: "middle", width: 130, },
    {title: "문서유형 명", field: "dl01", hozAlign: "left", vertAlign: "middle", width: 130, },
    {title: "문서번호", field: "doc", hozAlign: "left", vertAlign: "middle", width: 130, },
    {title: "GL일자(JD)", field: "dgj", visible: false, hozAlign: "left", vertAlign: "middle", width: 130, },
    {title: "GL일자", field: "gddgj", hozAlign: "left", vertAlign: "middle", width: 130, },
    {title: "작성일자(JD)", field: "dicj", visible: false, hozAlign: "left", vertAlign: "middle", width: 130, },
    {title: "작성일자", field: "gddicj", hozAlign: "left", vertAlign: "middle", width: 130, },
    {title: "세금일자(JD)", field: "dsvj", visible: false, hozAlign: "left", vertAlign: "middle", width: 130, },
    {title: "세금일자", field: "gddsvj", hozAlign: "left", vertAlign: "middle", width: 130, },
    {title: "사업단위", field: "dl02", hozAlign: "left", vertAlign: "middle", width: 130, },
    {title: "사업단위 명", field: "obj", hozAlign: "left", vertAlign: "middle", width: 130, },
    {title: "보조계정", field: "sub", hozAlign: "left", vertAlign: "middle", width: 130, },
    {title: "계정번호", field: "ani", hozAlign: "left", vertAlign: "middle", width: 130, },
    {title: "계정명칭", field: "dl03", hozAlign: "left", vertAlign: "middle", width: 210, },
    {title: "차대변 통합", field: "aa", hozAlign: "left", vertAlign: "right", width: 130, visible: false, },
    {title: "차변", field: "aaDebit", hozAlign: "right", vertAlign: "middle", width: 130,
        bottomCalc:"sum", bottomCalcParams:{precision:false},
        bottomCalcFormatter :"money", bottomCalcFormatterParams :{decimal:".", thousand:",", symbol:"₩", precision:false,},
        formatter:"money", formatterParams:{decimal:".", thousand:",", symbol:"₩", precision:false,},
     },
    {title: "대변", field: "aaCredit", hozAlign: "right", vertAlign: "middle", width: 130,
        bottomCalc:"sum", bottomCalcParams:{precision:false},
        bottomCalcFormatter :"money", bottomCalcFormatterParams :{decimal:".", thousand:",", symbol:"₩", precision:false,},
        formatter:"money", formatterParams:{decimal:".", thousand:",", symbol:"₩", precision:false,},
     },
    {title: "외화금액", field: "acr",  hozAlign: "right", vertAlign: "middle", width: 130,
        bottomCalc:"sum", bottomCalcParams:{precision:false},
        bottomCalcFormatter :"money", bottomCalcFormatterParams :{decimal:".", thousand:",", symbol:"₩", precision:false,},
        formatter:"money", formatterParams:{decimal:".", thousand:",", symbol:"₩", precision:false,},
    },
    {title: "통화코드", field: "crcd", hozAlign: "left", vertAlign: "middle", width: 130, },
    {title: "환율", field: "crr", hozAlign: "left", vertAlign: "middle", width: 130, },
    {title: "회계연도", field: "fy", hozAlign: "left", vertAlign: "middle", width: 130, },
    {title: "회계기간", field: "pn", hozAlign: "left", vertAlign: "middle", width: 130, },
    {title: "취소구분", field: "re", hozAlign: "left", vertAlign: "middle", width: 130, },
    {title: "이력일자(JD)", field: "hdgj", visible: false, hozAlign: "left", vertAlign: "middle", width: 130, },
    {title: "이력일자", field: "gdhdgj", hozAlign: "left", vertAlign: "middle", width: 130, },
    {title: "설명-비고", field: "exr", hozAlign: "left", vertAlign: "middle", width: 130, },
    {title: "설명이름", field: "exa", hozAlign: "left", vertAlign: "middle", width: 220, },
    {title: "오더유형", field: "dcto", hozAlign: "left", vertAlign: "middle", width: 130, },
    {title: "PO문서유형", field: "pdct", hozAlign: "left", vertAlign: "middle", width: 130, },
    {title: "구매오더", field: "po", hozAlign: "left", vertAlign: "middle", width: 130, },
    {title: "수량", field: "u", hozAlign: "left", vertAlign: "middle", width: 130, },
    {title: "단위항목", field: "um", hozAlign: "left", vertAlign: "middle", width: 130, },
    {title: "관리항목 유형", field: "sblt", hozAlign: "left", vertAlign: "middle", width: 130, },
    {title: "관리항목", field: "sbl", hozAlign: "left", vertAlign: "middle", width: 130, },
    {title: "관리항목 명", field: "dl09", hozAlign: "left", vertAlign: "middle", width: 130, },
    {title: "GL상쇄", field: "glc", hozAlign: "left", vertAlign: "middle", width: 130, },
    {title: "약식품목번호", field: "itm", hozAlign: "left", vertAlign: "middle", width: 130, },
    {title: "품목명칭", field: "dsc1", hozAlign: "left", vertAlign: "middle", width: 230, },
    {title: "세목", field: "exr1", hozAlign: "left", vertAlign: "middle", width: 130, },
    {title: "세율과세", field: "txa1", hozAlign: "left", vertAlign: "middle", width: 130, },
    {title: "송장번호", field: "vinv", hozAlign: "left", vertAlign: "middle", width: 130, },
    {title: "자산번호", field: "asid", hozAlign: "left", vertAlign: "middle", width: 130, },
    {title: "자산명칭", field: "dl04", hozAlign: "left", vertAlign: "middle", width: 130, }
];

let table = new Tabulator("#grid-table", {
    data:[],           //load row data from array
    layout:"fitColumns",      //fit columns to width of table
    height:"100%",
    width: "100%",
    renderHorizontal:"virtual",
    addRowPos:"top",          //when adding a new row, add it to the top of the table
    history:true,             //allow undo and redo actions on the table
    pagination:"local",       //paginate the data
    paginationSize:50,         //allow 10 rows per page of data
    paginationCounter:"rows", //display count of paginated rows in footer
    movableColumns:true,      //allow column order to be changed
    initialSort:[             //set the initial sort order of the data
        {column:"ifkey", dir:"asc"}, {column:"user", dir:"asc"}, {column:"ukid", dir:"asc"},
    ],
    columnDefaults:{
        tooltip:true,         //show tool tips on cells
    },
    columns:col,
    ajaxContentType: "json"
});



function objectifyForm(formArray) {//serializeArray data function
    var returnArray = {};
    for (var i = 0; i < formArray.length; i++) {
        returnArray[formArray[i]['name']] = formArray[i]['value'];
    }
    return returnArray;
}

function searchEApprovalList(){
    console.log('전자결재 상세자료 서버에서 조회하는 이벤트 trigger됨');
    let formData = new FormData(document.getElementById('eAppSearchFrm'));
    let formArr = objectifyForm($("#eAppSearchFrm").serializeArray());
    console.log('formArr=', formArr); // {ifkey: 'AA382564                                ', user: 'CON04               ', lt: 'AA  '}

    table.setData(
        "/eApproval/rest/search",
        formArr,
        "POST"
    );

}

document.addEventListener('DOMContentLoaded', function() {
    console.log('page load completed.');
    searchEApprovalList();
}, false);




