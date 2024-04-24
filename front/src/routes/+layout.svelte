<script lang="ts">
	import '$lib/app.css';
	import rq from '$lib/rq/rq.svelte';

	const { children } = $props();
</script>

<header class="navbar bg-gray-50 shadow">
	<div class="flex-1">
		<div class="flex-none">
			<div class="dropdown">
				<div tabindex="0" role="button" class="btn btn-ghost btn-circle">
					<svg
						xmlns="http://www.w3.org/2000/svg"
						class="h-5 w-5"
						fill="none"
						viewBox="0 0 24 24"
						stroke="currentColor"
						><path
							stroke-linecap="round"
							stroke-linejoin="round"
							stroke-width="2"
							d="M4 6h16M4 12h16M4 18h7"
						/></svg
					>
				</div>
				<ul
					tabindex="0"
					class="menu menu-sm dropdown-content mt-3 z-[1] p-2 shadow bg-base-100 rounded-box w-52"
				>
					{#if rq.isLogout()}
						<li>
							<a class="font-semi-bold" href="/member/login"
								><i class="fa-solid fa-right-to-bracket"></i> 로그인</a
							>
						</li>
					{/if}
					{#if rq.isLogin()}
						<li class="font-semi-bold">
							<button on:click={() => rq.logout()}>
								<i class="fa-solid fa-right-from-bracket"></i> 로그아웃
							</button>
						</li>
					{/if}
				</ul>
			</div>
		</div>
		<div class="flex-1"></div>
	</div>
	<div class="flex-1 flex justify-center">
		<a href="/" class="font-bold">Daily</a>
	</div>

	<div class="flex-1 justify-end mr-4">
		<div class="flex gap-x-4 relative items-center"></div>
	</div>
</header>
<main>{@render children()}</main>
<footer class=" bottom-0 w-full bg-white text-gray-300">푸터</footer>
