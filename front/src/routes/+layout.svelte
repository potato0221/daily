<script lang="ts">
	import '$lib/app.css';
	const { children } = $props();
</script>

<header class="navbar bg-gray-50 shadow">
	<div class="flex justify-center">daily</div>
</header>
<main class="m-2">{@render children()}</main>
<footer class=" bottom-0 w-full bg-white text-gray-300 m-2">푸터</footer>
