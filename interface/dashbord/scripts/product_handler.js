$("#products_productlist").ready(function () {

    $.ajax("http://localhost:8080/sellnbye/api/product", {
        contentType: 'application/json',
        type: 'GET'
    }).done(function (response) {
        var newItem = "";
        $.each(response, function (index, value) {

            newItem += `<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
            <div class="personal-info-wrap">
                <div class="widget-head-info-box">
                    <div class="persoanl-widget-hd">
                        <h2 class="username_header_id" >${value.productName}</h2>
                        <p>${value.creator}</p>
                    </div>
                    <img src="${value.productImage}" class="img-circle circle-border m-b-md center" alt="profile"  width="150" height="150">
                    <div class="social-widget-result">
                        <span>${value.productPrice}</span> <br>
                        <span><button type="button" id="user_editUser_btn" class="btn btn-primary">
                        EDIT
                    </button></span> |
                        <span> <button type="button" id="user_deleteUser_btn" class="btn btn-danger">
                        DELETE
                    </button></span>
                       
                      
                    </div>
                </div>
                
            </div>
        </div>`;
        });

        $("#products_productlist").append(newItem);
    });
});
