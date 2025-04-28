import { useMutation } from "@tanstack/react-query";
import { logout } from "../services/authService";
import { APP_BASE_PATH } from "../config/constants";

export function useLogout() {
    return useMutation({
        mutationFn: logout,
        onSuccess: () => {
            console.log('Déconnexion réussie');
            window.location.href = `${APP_BASE_PATH}/login.jsp`;
        },
        onError: (error) => {
            console.error('Erreur pendant la déconnexion:', error);
        },
    });
}
