<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lựa Chọn Gia Vị Sandwich</title>
    <!-- Tailwind CSS CDN -->
    <script src="https://cdn.tailwindcss.com"></script>
    <!-- FontAwesome 6 Icons CDN -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <!-- Google Fonts Inter -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;500;600;700;800&display=swap" rel="stylesheet">
    <style>
        html, body {
            width: 100vw;
            min-height: 100vh;
            margin: 0;
            padding: 0;
            background-color: #020617 !important;
            color: #f8fafc;
            font-family: 'Inter', sans-serif;
            overflow-x: hidden;
        }
    </style>
</head>
<body class="w-full min-h-screen flex items-center justify-center p-4">

<!-- Ambient Glow Background -->
<div class="fixed top-1/2 left-1/2 -translate-x-1/2 -translate-y-1/2 w-[500px] h-[500px] bg-amber-500/15 rounded-full blur-[120px] pointer-events-none"></div>

<!-- Main Card Container -->
<div class="relative w-full max-w-lg bg-slate-900/90 backdrop-blur-2xl rounded-3xl border border-slate-800 shadow-2xl p-6 sm:p-8 space-y-6 z-10">

    <!-- Header -->
    <div class="text-center space-y-2">
        <div class="inline-flex items-center justify-center w-16 h-16 rounded-2xl bg-gradient-to-tr from-amber-500 to-orange-400 p-0.5 shadow-lg shadow-amber-500/20">
            <div class="w-full h-full bg-slate-950 rounded-[14px] flex items-center justify-center">
                <i class="fa-solid fa-burger text-2xl text-amber-400"></i>
            </div>
        </div>
        <h1 class="text-2xl sm:text-3xl font-extrabold bg-gradient-to-r from-white via-slate-200 to-slate-400 bg-clip-text text-transparent">
            Sandwich Condiments
        </h1>
        <p class="text-xs sm:text-sm text-slate-400">
            Lựa chọn các loại gia vị ăn kèm cho chiếc Sandwich của bạn
        </p>
    </div>

    <!-- Form Checkboxes -->
    <form action="${pageContext.request.contextPath}/save" method="post" class="space-y-6">

        <div class="grid grid-cols-1 sm:grid-cols-2 gap-3">

            <!-- Checkbox 1: Lettuce -->
            <label class="flex items-center p-3.5 bg-slate-800/60 border border-slate-700/80 rounded-xl cursor-pointer hover:border-amber-500/50 hover:bg-slate-800 transition duration-200 group">
                <input type="checkbox" name="condiment" value="Lettuce"
                       class="w-5 h-5 rounded border-slate-600 text-amber-500 focus:ring-amber-500/30 bg-slate-900">
                <span class="ml-3 text-sm font-medium text-slate-200 group-hover:text-white flex items-center gap-2">
                        <i class="fa-solid fa-seedling text-emerald-400"></i> Lettuce (Rau xà lách)
                    </span>
            </label>

            <!-- Checkbox 2: Tomato -->
            <label class="flex items-center p-3.5 bg-slate-800/60 border border-slate-700/80 rounded-xl cursor-pointer hover:border-amber-500/50 hover:bg-slate-800 transition duration-200 group">
                <input type="checkbox" name="condiment" value="Tomato"
                       class="w-5 h-5 rounded border-slate-600 text-amber-500 focus:ring-amber-500/30 bg-slate-900">
                <span class="ml-3 text-sm font-medium text-slate-200 group-hover:text-white flex items-center gap-2">
                        <i class="fa-solid fa-apple-whole text-rose-400"></i> Tomato (Cà chua)
                    </span>
            </label>

            <!-- Checkbox 3: Mustard -->
            <label class="flex items-center p-3.5 bg-slate-800/60 border border-slate-700/80 rounded-xl cursor-pointer hover:border-amber-500/50 hover:bg-slate-800 transition duration-200 group">
                <input type="checkbox" name="condiment" value="Mustard"
                       class="w-5 h-5 rounded border-slate-600 text-amber-500 focus:ring-amber-500/30 bg-slate-900">
                <span class="ml-3 text-sm font-medium text-slate-200 group-hover:text-white flex items-center gap-2">
                        <i class="fa-solid fa-jar text-amber-400"></i> Mustard (Mù tạt)
                    </span>
            </label>

            <!-- Checkbox 4: Sprouts -->
            <label class="flex items-center p-3.5 bg-slate-800/60 border border-slate-700/80 rounded-xl cursor-pointer hover:border-amber-500/50 hover:bg-slate-800 transition duration-200 group">
                <input type="checkbox" name="condiment" value="Sprouts"
                       class="w-5 h-5 rounded border-slate-600 text-amber-500 focus:ring-amber-500/30 bg-slate-900">
                <span class="ml-3 text-sm font-medium text-slate-200 group-hover:text-white flex items-center gap-2">
                        <i class="fa-solid fa-plant-wilt text-teal-400"></i> Sprouts (Rau mầm)
                    </span>
            </label>

        </div>

        <!-- Submit Button -->
        <button type="submit"
                class="w-full bg-gradient-to-r from-amber-500 to-orange-500 hover:from-amber-400 hover:to-orange-400 text-slate-950 font-bold py-3.5 px-4 rounded-xl shadow-lg shadow-amber-500/25 transition duration-200 flex items-center justify-center gap-2 cursor-pointer">
            <i class="fa-solid fa-check text-slate-950"></i>
            <span>Lưu Lựa Chọn (Save)</span>
        </button>
    </form>

    <div class="text-center pt-2 border-t border-slate-800">
        <p class="text-[11px] text-slate-500">
            Ứng dụng Spring MVC Sandwich Condiment Selection
        </p>
    </div>

</div>

</body>
</html>