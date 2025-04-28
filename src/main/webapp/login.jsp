<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <!-- Tailwind CSS via CDN -->
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="min-h-screen flex items-center justify-center bg-gray-100">
<div class="bg-white p-10 rounded-xl shadow-lg w-full max-w-sm">

    <!-- Logo (optional) -->
    <div class="flex justify-center mb-6">
        <img src="https://via.placeholder.com/60" alt="Logo" class="h-16 w-16 rounded-full">
    </div>

    <h1 class="text-2xl font-semibold text-center text-gray-800 mb-2">Sign in to your account</h1>
    <p class="text-center text-gray-500 text-sm mb-6">Welcome back, please enter your credentials.</p>

    <% if (request.getParameter("error") != null) { %>
    <div class="bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded mb-4 text-center" role="alert">
        Invalid credentials, please try again.
    </div>
    <% } %>

    <form method="post" action="${pageContext.request.contextPath}/api/v1/auth/login" class="space-y-5">
        <div>
            <label for="username" class="block text-gray-700 font-medium mb-1">Username</label>
            <input type="text" id="username" name="username"
                   class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-transparent"
                   required />
        </div>
        <div>
            <label for="password" class="block text-gray-700 font-medium mb-1">Password</label>
            <input type="password" id="password" name="password"
                   class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-transparent"
                   required />
        </div>
        <div>
            <button type="submit"
                    class="w-full bg-blue-600 hover:bg-blue-700 text-white font-semibold py-2 px-4 rounded-lg transition duration-300">
                Sign In
            </button>
        </div>
    </form>
</div>
</body>
</html>
