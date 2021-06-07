function newAcc() {
    let bal = $('#amount').val();

    if (incorrectAmount(bal)) {
        return;
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
                    window.location.href = '/';
                }
            })
}

function transfer() {
    const am = $('#trans-amount').val();

    if (incorrectAmount(am)) {
        return;
    }

    const transaction = {
        fromAccNumber: $('#number-from').val().substring(0, 20),
        toAccNumber: $('#number-to').val().substring(0, 20),
        amount: $('#trans-amount').val()
    }

    $.ajax({
                method: 'post',
                url: '/transfer',
                contentType: 'application/json',
                data: JSON.stringify(transaction),
                success: () => {
                    $('#trans-amount').val('');
                    window.location.href = '/';
                },
                error: (e) => {
                    $('.con_alrt').append('<div class="alert alert-danger alert-dismissible fade show mt-4" role="alert">' + e.responseText + '</div>');
                    const alrt = $('.alert');

                    setTimeout(function(){
                        alrt.remove();
                    }, 4000);
                }
            })
}

function incorrectAmount(am) {
    if (am == '' || am == '0' || isNaN(parseFloat(am))) {
            $('.con_alrt').append('<div class="alert alert-danger alert-dismissible fade show" role="alert">Incorrect amount</div>');
            const alrt = $('.alert');

            setTimeout(function(){
                alrt.remove();
            }, 4000);
            return true;
        }
    return false;
}