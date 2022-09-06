class AppPage {

    static DOMAIN = location.origin;

    static BASE_URL = this.DOMAIN + "/api";
    static BASE_URL_AUTH = this.BASE_URL + "/auth";
    static BASE_URL_ROLE = this.DOMAIN + "/api/roles";

    static BASE_URL_CATEGORY = this.BASE_URL + "/categories";
    static BASE_URL_PRODUCT = this.BASE_URL + "/products";
    static BASE_URL_CART = this.BASE_URL + "/carts";
    static BASE_URL_CARTITEM = this.BASE_URL + "/cartitems";

    static BASE_URL_CLOUD_IMAGE = "https://res.cloudinary.com/dawjpku04/image/upload";
    static BASE_URL_CLOUD_VIDEO = "https://res.cloudinary.com/dawjpku04/video/upload";
    static BASE_SCALE_IMAGE = "c_limit,w_150,h_100,q_100"

    static AlertMessageEn = class {
        static SUCCESS_CREATED = "Successful data generation !";
        static SUCCESS_UPDATED = "Data update successful !";
        static SUCCESS_DEPOSIT = "Deposit transaction successful !";
        static SUCCESS_WITHDRAW = "Withdrawal transaction successful !";
        static SUCCESS_TRANSFER = "Transfer transaction successful !";
        static SUCCESS_DEACTIVATE = "Deactivate the customer successfully !";

        static ERROR_400 = "The operation failed, please check the data again.";
        static ERROR_401 = "Unauthorized - Your access token has expired or is not valid.";
        static ERROR_403 = "Forbidden - You are not authorized to access this resource.";
        static ERROR_404 = "Not Found - The resource has been removed or does not exist";
        static ERROR_500 = "Internal Server Error - The server system is having problems or cannot be accessed.";

        static ERROR_LOADING_PROVINCE = "Loading list of provinces - cities failed !";
        static ERROR_LOADING_DISTRICT = "Loading list of district - ward failed !"
        static ERROR_LOADING_WARD = "Loading list of wards - communes - towns failed !";
    }

    static AlertMessageVi = class {
        static SUCCESS_CREATED = "Tạo dữ liệu thành công !";
        static SUCCESS_UPDATED = "Cập nhật dữ liệu thành công !";
        static SUCCESS_DEPOSIT = "Giao dịch gửi tiền thành công !";
        static SUCCESS_WITHDRAW = "Giao dịch rút tiền thành công !";
        static SUCCESS_TRANSFER = "Giao dịch chuyển khoản thành công !";
        static SUCCESS_DEACTIVATE = "Hủy kích hoạt khách hàng thành công !";

        static ERROR_400 = "Thao tác không thành công, vui lòng kiểm tra lại dữ liệu.";
        static ERROR_401 = "Unauthorized - Access Token của bạn hết hạn hoặc không hợp lệ.";
        static ERROR_403 = "Forbidden - Bạn không được quyền truy cập tài nguyên này.";
        static ERROR_404 = "Not Found - Tài nguyên này đã bị xóa hoặc không tồn tại";
        static ERROR_500 = "Internal Server Error - Hệ thống Server đang có vấn đề hoặc không truy cập được.";

        static ERROR_LOADING_PROVINCE = "Tải danh sách tỉnh - thành phố không thành công !";
        static ERROR_LOADING_DISTRICT = "Tải danh sách quận - phường - huyện không thành công !";
        static ERROR_LOADING_WARD = "Tải danh sách phường - xã - thị trấn không thành công !";
    }

    static SweetAlert = class {
        static showDeactivateConfirmDialog() {
            return Swal.fire({
                icon: 'warning',
                text: 'Are you sure to deactivate the selected customer ?',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Yes, please deactivate this client !',
                cancelButtonText: 'Cancel',
            })
        }

        static showSuccessAlert(t) {
            Swal.fire({
                icon: 'success',
                title: t,
                position: 'top-end',
                showConfirmButton: false,
                timer: 1500
            })
        }

        static showErrorAlert(t) {
            Swal.fire({
                icon: 'error',
                title: 'Warning',
                text: t,
            })
        }

        static showError401() {
            Swal.fire({
                icon: 'error',
                title: 'Access Denied',
                text: 'Invalid credentials!',
            })
        }

        static showError403() {
            Swal.fire({
                icon: 'error',
                title: 'Access Denied',
                text: 'You are not authorized to perform this function!',
            })
        }
    }

    static IziToast = class {
        static showSuccessAlert(m) {
            iziToast.success({
                title: 'OK',
                position: 'topRight',
                timeout: 2500,
                message: m
            });
        }

        static showErrorAlert(m) {
            iziToast.error({
                title: 'Error',
                position: 'topRight',
                timeout: 2500,
                message: m
            });
        }
    }

    static Notify = class {
        static showSuccessAlert(m) {
            $.notify(m, "success");
        }

        static showErrorAlert(m) {
            $.notify(m, "error");
        }
    }

    static drawProducts = (obj) => {
        let str = `
            <div class="card col-lg-2">
                <img src="${AppPage.BASE_URL_CLOUD_IMAGE + '/' + AppPage.BASE_SCALE_IMAGE + '/' + obj.fileFolder + '/' + obj.avatar}" class="card-img-top" alt="...">
                <div class="card-body">
                    <h5 class="card-title">${obj.title}</h5>
                    <p class="card-text">${obj.price}</p>
                    <button class="btn btn-primary btn-add-card" data-id="${obj.id}">
                        Add to card
                    </button>
                </div>
            </div>
        `;

        return str;
    }

    static drawCartItems = (obj) => {
        let str = `
            <div class="card mb-3" style="max-width: 400px;">
                <div class="row g-0">
                    <div class="col-md-4">
                        <img src="${AppPage.BASE_URL_CLOUD_IMAGE + '/' + AppPage.BASE_SCALE_IMAGE + '/' + obj.fileFolder + '/' + obj.avatar}" class="img-fluid rounded-start" alt="...">
                    </div>
                    <div class="col-md-8">
                        <div class="card-body">
                            <h5 class="card-title">${obj.title}</h5>
                            <p class="card-text">${obj.price}</p>
                            <p class="card-text">
                                <span>
                                    <button class="btn btn-warning col-md-2">
                                        <i class="fa fa-minus" aria-hidden="true"></i>
                                    </button>
                                </span>
                                <input type="text" class="col-md-3 num-space" value="${obj.quantity}">
                                <span>
                                    <button class="btn btn-success col-md-2">
                                        <i class="fa fa-plus" aria-hidden="true"></i>
                                    </button>
                                </span>
                            </p>
                            <p class="card-text">${obj.amount}</p>
                        </div>
                    </div>
                </div>
            </div>
        `;

        return str;
    }

    static formatNumber() {
        $(".num-space").number(true, 0, ',', ' ')
        $(".num-point").number(true, 0, ',', '.');
        $(".num-comma").number(true, 0, ',', ',');
    }

    static formatNumberSpace(x) {
        if (x == null) {
            return x;
        }
        return x.toString().replace(/ /g, "").replace(/\B(?=(\d{3})+(?!\d))/g, " ");
    }

    static removeFormatNumberSpace(x) {
        if (x == null) {
            return x;
        }
        return x.toString().replace(/ /g, "")
    }

    static formatTooltip() {
        $('[data-toggle="tooltip"]').tooltip();
    }
}


class Product {
    constructor(id, title, slug, image, price, sold,  category ) {
        this.id = id;
        this.title = title;
        this.slug = slug;
        this.image = image;
        this.price = price;
        this.sold = sold;
        this.category = category;
    }
}



class Role {
    constructor(id, code) {
        this.id = id;
        this.code = code;
    }
}
class User {
    constructor(username,password,role){
        this.username=username;
        this.password=password;
        this.role=role;
    }
}

class Category {
    constructor(id, title) {
        this.id = id;
        this.title = title;
    }
}


class LocationRegion {
    constructor(id, provinceId, provinceName, districtId, districtName, wardId, wardName, address) {
        this.id = id;
        this.provinceId = provinceId;
        this.provinceName = provinceName;
        this.districtId = districtId;
        this.districtName = districtName;
        this.wardId = wardId;
        this.wardName = wardName;
        this.address = address;
    }
}

class Customer {
    constructor(id, fullName, email, phone, locationRegion, balance) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.locationRegion = locationRegion;
        this.balance = balance;
    }
}


