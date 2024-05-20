import * as RxDialog from "@radix-ui/react-dialog";
import { IoMdCloseCircleOutline } from "react-icons/io";

export function Dialog({ children }: RxDialog.DialogProps) {
  return <RxDialog.Root>{children}</RxDialog.Root>;
}

export function DialogTrigger({ children }: RxDialog.DialogTriggerProps) {
  return <RxDialog.Trigger asChild>{children}</RxDialog.Trigger>;
}

export function DialogContent({ children }: RxDialog.DialogContentProps) {
  return (
    <RxDialog.Portal>
      <RxDialog.Overlay className="bg-blackA6 data-[state=open]:animate-overlayShow fixed inset-0" />
      <RxDialog.Content className="data-[state=open]:animate-contentShow fixed top-[50%] left-[50%] max-h-[85vh] w-[90vw] max-w-[450px] translate-x-[-50%] translate-y-[-50%] rounded-[6px] bg-white p-[25px] shadow-[hsl(206_22%_7%_/_35%)_0px_10px_38px_-10px,_hsl(206_22%_7%_/_20%)_0px_10px_20px_-15px] focus:outline-none">
        {children}
				<RxDialog.Close asChild>
          <button
            className="btn absolute top-[10px] right-[10px] inline-flex h-[25px] w-[25px] appearance-none items-center justify-center rounded-full"
            aria-label="Close"
          >
            <IoMdCloseCircleOutline />
          </button>
        </RxDialog.Close>
      </RxDialog.Content>
    </RxDialog.Portal>
  );
}

export function DialogTitle({ children }: RxDialog.DialogTitleProps) {
	return <RxDialog.Title className="m-0 text-[17px] font-medium">{children}</RxDialog.Title>;
}

export function DialogDescription({ children }: RxDialog.DialogDescriptionProps) {
	return <RxDialog.Description className="mt-[10px] mb-5 text-[15px] leading-normal">{children}</RxDialog.Description>;
}

export function DialogClose({ children }: RxDialog.DialogCloseProps) {
	return <RxDialog.Close asChild>{children}</RxDialog.Close>;
}
