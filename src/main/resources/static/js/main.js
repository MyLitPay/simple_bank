
//$(function() {
//    getPosts();
//});

//$('#show_all').click(() => {
//    $.ajax({
//            method: 'get',
//            url: '/api/acc/all',
//            success: () => {
//                $('#post_text').val('');
//                getPosts();
//
//            }
//        })
//
//
//    const newCase = {
//        name: $('#post_text').val()
//    }

//    if ($('#post_text').val() === '') {
//        alert('Type name of the case')
//    } else {
//        $.ajax({
//            method: 'post',
//            url: '/api/cases',
//            contentType: 'application/json',
//            data: JSON.stringify(newCase),
//            success: () => {
//                $('#post_text').val('');
//                getPosts();
//
//            }
//        })
//    }
//});

//function getList() {
//    $.ajax({
//        method: 'get',
//        url: '/api/acc/all'
//        })
//}
function newAcc() {
let bal = $('#amount').val();

if (isNaN(parseFloat(bal))) {
alert(bal + ' is not a number')
}

const acc = {
        balance: bal,
        currency: $('#currency').val()
    }
$.ajax({
            method: 'post',
            url: '/new',
            contentType: 'application/json',
            data: JSON.stringify(acc),
            success: () => {
                $('#amount').val('');
            }
        })
}


//function getList() {

//$('.btn').click(() => {
//    $.ajax({
//        method: 'get',
//        url: '/default/all'
//        success: (data) => {

//            if (data.length != 0) {
//                $('.no-cases').css({'display': 'none'});
//            } else {
//                $('.no-cases').css({'display': 'block'});
//            }

//            $('.card_wrapper').remove();

//            $.ajax({
//                method: 'get',
//                url: '/default/all'
//            });

//            for (acc of data) {
////                const id = case1.id;
////                const name = case1.name;
//                const accountNumber = acc.accountNumber;
//                const balance = acc.balance;
//                const currency = acc.currency;
//
//                console.log(accountNumber);
//                console.log(balance);
//                console.log(currency);

//                const div = $('<div/>', {
//                    id: id,
//                    class:  'card_wrapper',
//                });
//                $('#button_delete').after(div);
//
//                const h2 = $('<h2/>', {
//                    id: 'nam' + id,
//                    text: name
//                })
//
//                const div2 = $('<div/>', {
//                    id: 'but_wrap' + id,
//                    class: 'buttons_wrapper'
//                })
//                div.append(h2);
//                div.append(div2);
//
//                const a1 = $('<a/>', {
//                    id: 'button_update' + id,
//                    class: 'button button_update',
//                    text: 'Edit'
//                })
//
//                const a2 = $('<a/>', {
//                    id: 'button_delete_one' + id,
//                    class: 'button button_delete',
//                    text: 'Delete'
//                })
//                div2.append(a1);
//                div2.append(a2);
//            }
//        }
//    })
//}