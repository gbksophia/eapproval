/*
* 2023.03.10 윌러스 김근비
* main_index.mustache
* 하이트진로 전표 전자결재 상신 상세자료 조회 페이지에서 사용되는 JS입니다.
*
* */


let tableData = [];

// 페이지 로드시에 서버에서 자동으로 전자결재 자료 데이터를 가져와서 tableData에 매핑.
console.log('그리드 컬럼 설정 및 그리드 초기 생성 이전에 서버에서 데이터 가져오는 로직 작동됨');
searchEApprovalList_PageLoad();

// 그리드 컬럼 설정
let col = [
    {formatter:"rownum", hozAlign:"center", width:40},
    {title: "id", field: "id", visible: false, hozAlign: "left", vertAlign: "middle", visible: false, },
    {title: "전자결재KEY", field: "ifkey", hozAlign: "left", vertAlign: "middle", width: 130, visible: false,},
    {title: "User", field: "user", hozAlign: "left", vertAlign: "middle", width: 130, visible: false, },
    {title: "Ukid", field: "ukid", visible: false, hozAlign: "left", vertAlign: "middle", width: 130, visible: false, },
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
    {title: "계정명칭", field: "dl03", hozAlign: "left", vertAlign: "middle", width: 210,
        bottomCalc: function (){return '합계';},
        bottomCalcFormatter: function (cell){
            let text =  cell.getValue();
            return "<div style='text-align:center;'>" + text + "</div>";
        },
    },
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
        formatter:"money",
        formatterParams: function (cell){
            let row = cell.getRow();
            let currencyCode = row.getCell("crcd").getValue();
            if(currencyCode === 'KRW'){
                return {decimal:".", thousand:",", symbol:"₩", precision:0,};
            }else if(currencyCode === 'EUR'){
                return {decimal:".", thousand:",", symbol:"€", precision:2,};
            }else if(currencyCode === 'USD'){
                return {decimal:".", thousand:",", symbol:"$", precision:2,};
            }else if(currencyCode === 'CNY'){
                return {decimal:".", thousand:",", symbol:"¥", precision:2,};
            }else if(currencyCode === 'JPY'){
                return {decimal:".", thousand:",", symbol:"¥", precision:0,};
            }else{
                return {decimal:".", thousand:",", symbol:"", precision:2,};
            }
        }
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

// 그리도 초기 생성 기능
let table = new Tabulator("#grid-table", {
    data:tableData,           //load row data from array
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
    ajaxContentType: "json",
});


function objectifyForm(formArray) {//serializeArray data function
    let returnArray = {};
    for (let i = 0; i < formArray.length; i++) {
        returnArray[formArray[i]['name']] = formArray[i]['value'];
    }
    return returnArray;
}


function getSearchKeyWord(){
    //console.log('Form에서 검색조건 가져오는 로직');
    let formArr = objectifyForm($("#eAppSearchFrm").serializeArray());
    return formArr;
}

function searchEApprovalList_PageLoad(){

    console.log('자동으로 서버에서 전자결재 상세자료를 조회하는 이벤트 trigger됨');
    let formArr = getSearchKeyWord();
    //console.log('formArr=', formArr);

    $.ajax({
        url: '/eApproval/rest/search',
        type: 'POST',
        datatype:'json',
        contentType : "application/json; charset=UTF-8",
        data: JSON.stringify(formArr),
        async: false,
        success: (result) => {
            tableData = result;
        }
    });

}


function searchEApprovalList_Button(){

    console.log('검색버튼 클릭하여 서버에서 전자결재 상세자료 조회하는 이벤트 trigger됨');
    let formArr = getSearchKeyWord();

    table.setData(
        "/eApproval/rest/search",
        formArr,
        "POST"
    );

}

// 새로고침이나 URL에서 전자결재Key와 결재요청자 값 변경해서 페이지 들어갈때 작동하는 로직
document.addEventListener('DOMContentLoaded', function() {
    console.log('[DOMContentLoaded] page load completed.');
    //searchEApprovalList_PageLoad();
}, false);




