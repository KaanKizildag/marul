import {ElNotification} from "element-plus";

export default function () {

    const successResponse = (message) => {
        ElNotification({
            title: 'Başarılı',
            message: message,
            type: 'success',
            position: 'bottom-right',
        })
    }

    const errorResponse = (message) => {
        ElNotification({
            title: 'Hata',
            message: message,
            type: 'error',
            position: 'bottom-right',
        })
    }

    return {successResponse, errorResponse}
}