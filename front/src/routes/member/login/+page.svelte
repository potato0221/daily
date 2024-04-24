<script>
	import rq from '$lib/rq/rq.svelte';
</script>

<div class="flex flex-col gap-20 items-center justify-center p-4">
	<div class="space-y-4">
		<div class="flex justify-center">
			<a href={rq.getKakaoLoginUrl()}>
				<img src="/kakao_login_medium_narrow.png" alt="" />
			</a>
		</div>
	</div>
</div>
